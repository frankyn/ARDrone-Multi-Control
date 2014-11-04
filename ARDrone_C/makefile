CC=gcc
CFLAGS=-c -Wall 
EFLAGS=
LDFLAGS=
BUILDDIRS= $(DIRS:%=%)
SOURCES= main.c
OBJECTS= $(SOURCES:.c=.o)
EXECUTABLE= ARDroneTest

all: $(SOURCES) $(EXECUTABLE)

clean: 
	rm *.o

$(EXECUTABLE): $(OBJECTS)
	$(clean)
	$(CC) $(LDFLAGS) $(OBJECTS) -o $@ $(EFLAGS)

.cpp.o:
	$(CC) $(CFLAGS) $< -o $@
