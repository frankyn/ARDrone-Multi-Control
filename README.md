# ARDrone_Multiple Drones
A project revolved around working with multiple AR. Drone Parrot UAVs within an Android app. The build contains the configuration for the following configuration. The reason behind using Infrastructure mode within the AR. Drones is to allow multiple drone communication from Android Device to all Drones. It doesn't require a proxy just a personal hotspot within the device if possible. 

# Environment Setup:
Once environment is setup, then the code IP’s must be updated as of this update.

# Setting up drone environment:
Assumption is drones are brand-new:

> denotes a command in a terminal or telnet capable program.

1. Download source from: https://sites.google.com/site/androflight/open-source
2. Extract source
3. cd sourceFile/src/data/ (Keep this directory open during the rest of these instructions)
4. Open install.sh (Change to the following)
5. ESSID=AndroidAP
6. IP=192.168.43.10
7. NETMASK=255.255.255.0
8. Save install.sh
9. Turn on Drone
10. Connect to Drone Access Point with a wireless-capable computer.

> telnet 192.168.1.1 (No password required)
> vi install.sh (Copy and paste the install.sh on our local machine to vi)

> vi ARAutoConnect.sh (Copy and paste the ARAutoConnect.sh on our local machine to vi)

> vi uninstall.sh (Copy and paste the uninstall.sh)

> chmod 0755 install.sh
> chmod 0755 ARAut	oConnect.sh
> chmod 0755 uninstall.sh
> ./install.sh
> reboot (Disconnection should occur and will then join ESSID once finished)

# Drone Environment References:
1. https://sites.google.com/site/androflight/arautoconnect
2. http://drones.johnback.us/blog/2013/02/03/programming-multiple-parrot-a-dot-r-drones-on-one-network-with-node-dot-js/
3. http://forum.xda-developers.com/galaxy-s2/general/guide-ar-drone-running-samsung-galaxy-s2-t1092455
