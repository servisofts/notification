#!/bin/bash
user_ss="servisofts"
host_ss="192.168.2.2"
path_server="/home/servisofts/servicios/notification/entornos/notification/servicios/notification/"

# scp "./servisofts.jks" "$user_ss@$host_ss:$path_server"
# scp "./config.json" "$user_ss@$host_ss:$path_server"
# scp "./servicio.pem" "$user_ss@$host_ss:$path_server"
scp "./server.jar" "$user_ss@$host_ss:$path_server/server.jar"
# scp -r "./query" "$user_ss@$host_ss:$path_server/"