/* 
 * File:   UDP.c
 * Author: prat
 *
 * Created on November 4, 2011, 1:48 PM
 */

#include <stdio.h>
#include <stdlib.h>
#include <arpa/inet.h>
#include <netinet/in.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <unistd.h>
#include <time.h>
#include <string.h>
#include <fcntl.h>
#include <pthread.h>


#define PORT 5556
#define WIFI_ARDRONE_IP "192.168.1.200"
#define WIFI_MYKONOS_IP "192.168.1.200"
#define WIFI_MYKONOS2_IP "192.168.1.202"
#define NAVDATA_SEQUENCE_DEFAULT  1
#define NAVDATA_PORT              5556
#define NAVDATA_HEADER            0x55667788
#define NAVDATA_BUFFER_SIZE       2048

pthread_t threads[2];

static int navdata_udp_socket  = -1;
static const char ardrone_1_ip[] = "192.168.1.200";
static const char ardrone_2_ip[] = "192.168.1.202";

static void navdata_write (int8_t *buffer, int32_t len, char * ardrone_ip )
{
    struct sockaddr_in to;
    int32_t flags;

    if (navdata_udp_socket < 0)
        {
            /// Open udp socket to broadcast at commands to other mykonos
            struct sockaddr_in navdata_udp_addr;

            memset( (char*)&navdata_udp_addr, 0, sizeof(navdata_udp_addr) );

            navdata_udp_addr.sin_family      = AF_INET;
            navdata_udp_addr.sin_addr.s_addr = INADDR_ANY;
            navdata_udp_addr.sin_port        = htons(NAVDATA_PORT + 100);

            navdata_udp_socket = socket( AF_INET, SOCK_DGRAM, 0 );

            if( navdata_udp_socket >= 0 )
                {
                    flags = fcntl(navdata_udp_socket, F_GETFL, 0);
                    if( flags >= 0 )
                        {
                            flags |= O_NONBLOCK;

                            flags = fcntl(navdata_udp_socket, F_SETFL, flags );
                        }
                    else
                        {
                            printf("Get Socket Options failed\n");
                        }

                    if (bind(navdata_udp_socket, (struct sockaddr*)&navdata_udp_addr, sizeof(struct sockaddr)) < 0) {
                        
                    }
                }
        }
    if( navdata_udp_socket >= 0 ) {
        int res;

        memset( (char*)&to, 0, sizeof(to) );
        to.sin_family       = AF_INET;
        to.sin_addr.s_addr  = inet_addr(ardrone_ip); // BROADCAST address for subnet 192.168.1.xxx
        to.sin_port         = htons( NAVDATA_PORT );

        res = sendto( navdata_udp_socket, (char*)buffer, len, 0, (struct sockaddr*)&to, sizeof(to) );
    }
}

static void navdata_open_server (char * ip)
{
    int32_t one = 1;
    navdata_write ((int8_t*)&one, sizeof( one ), ip);
}

void * run_drone ( void * ip ) {
    struct sockaddr_in si_me, si_other,to;
    int slen, tlen, llen, clen, s,i;
    char config[] = "AT*CONFIG=100,\"control:altitude_max\",\"2000\"\r";
    char takeoff[]= "AT*REF=101,290718208\r";
    char land[]= "AT*REF=102,290717696\r";
    char land_emergency[] = "AT*REF=1,290717696\rAT*REF=2,290717952\rAT*REF=3,290717696\r";
    char decrease_height[] = "AT*PCMD=103,1,0,0,-1110651699,0\r";
    char increase_height[] = "AT*PCMD=104,1,0,0,1036831949,0\r";
    char rotate_left[] = "AT*PCMD=105,1,0,0,0,1036831949\r";
    char rotate_right[] = "AT*PCMD=106,1,0,0,0,1036831949\r";
    char forward[] = "AT*PCMD=107,1,1065353216,0,0,0\r";
    char backward[] = "AT*PCMD=108,1,1065353216,0,0,0\r";
    char forward1[] = "AT*PCMD=109,1,1065353216,0,0,0\r";
    char backward1[] = "AT*PCMD=110,1,1065353216,0,0,0\r";
    char forward2[] = "AT*PCMD=111,1,1065353216,0,0,0\r";
    char backward2[] = "AT*PCMD=112,1,1065353216,0,0,0\r";
    
    char fly_left[] = "AT*PCMD=109,1,0,-1110651699,0,0\r";
    char fly_right[] = "AT*PCMD=110,1,0,1036831949,0,0\r";


    tlen = strlen( takeoff );
    llen = strlen( land );
    clen = strlen( config );
    
    navdata_open_server ((char*)ip);
    printf("taking off\n");
    navdata_write ((int8_t*)takeoff, tlen,(char*)ip);
    sleep(5);
    printf("left\n");
    tlen = strlen(forward);
    navdata_write ((int8_t*)forward, tlen,(char*)ip);
    printf("left\n");
    tlen = strlen(forward1);
    navdata_write ((int8_t*)forward2, tlen,(char*)ip);
    printf("left\n");
    tlen = strlen(forward2);
    navdata_write ((int8_t*)forward2, tlen,(char*)ip);
    sleep(5);
    printf("right\n");
    tlen = strlen(backward);
    navdata_write ((int8_t*)backward, tlen,(char*)ip);
    printf("right\n");
    tlen = strlen(backward1);
    navdata_write ((int8_t*)backward1, tlen,(char*)ip);
    printf("right\n");
    tlen = strlen(backward2);
    navdata_write ((int8_t*)backward2, tlen,(char*)ip);
    sleep(10);
    printf("landing\n");
    navdata_write ((int8_t*)land, llen,(char*)ip);

    pthread_exit(NULL);
}

int main()
{
    pthread_t rc1, rc2;
    // Create threads
    rc1 = pthread_create(&threads[0], NULL, run_drone, (void *)ardrone_1_ip); 
    rc2 = pthread_create(&threads[1], NULL, run_drone, (void *)ardrone_2_ip);

    pthread_join(rc1, NULL);
    pthread_join(rc2, NULL);

    /* Last thing that main() should do */
    pthread_exit(NULL);
    return 0;
}
