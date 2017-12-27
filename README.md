# Car-Simulation

In this project, each person in a group (group of 3) has an Android mobile device and an RPi (together simulates a car). All the cars in a group will drive on the same track together.
Each person is able to see ALL the cars from their group, and know their positions dynamically. 
Information about the mechanics for a particular car resides with the corresponding android device, but all the RPis in the same WiFi network will have to communicate with each other and dynamically exchange information about their cars in REAL TIME. This information is used by all the cars to know relevant details about the other cars.

Driving should be implemented in 2 modes:

City Driving Mode: where the RPi’s exchange information with each other, and if needed, override the control signals from their respective drivers to ensure that the cars do not crash.

Racing Mode: where the control signals from the drivers override the safe autonomous driving if needed.


There are three android projects for three cars (Resource, Resource1, Resource2).
Three RPis have same code for communication except the ip address.




