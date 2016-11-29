#!/bin/bash

# try different nmap scans to try and find the office desktop

# Normal
echo nmap 192.168.0.67?
read
nmap 192.168.0.67

# List scan
echo nmap -sL 192.168.0.67?
read
nmap -sL 192.168.0.67

# Disable ping
echo nmap -Pn 192.168.0.67
read
nmap -Pn 192.168.0.67

# "Engage the network with arbitrary combinations of multi-port
# TCP SYN/ACK, UDP, SCTP INIT and ICMP probes." - not that I
# know what most of that means
# Wait, I actually don't know how to put together this command
