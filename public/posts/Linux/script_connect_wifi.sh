#!/bin/bash

echo "Start script - $(date)" >> /home/user/Documentos/Start_script_so/log_script_wifi.txt
path='/home/user/Documentos/Script/conectar_wifi'
source $path/wifi_conect_venv/bin/activate
python3 $path/connet_wifi_.py
echo "End script - $(date)" >> /home/user/Documentos/Start_script_so/log_script_wifi.txt 2>&1

# '>>' Write in a file the text: "End script - $(date)"
# '2>&1' Redirects standard error (errors) to the file.
exit