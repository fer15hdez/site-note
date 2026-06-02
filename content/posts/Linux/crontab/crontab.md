## Sintaxis 
***min hour  dom(day of month) month dow(day of week)   command***
*Is important specify the progrmang that execute the script*

### Example
*Is executed when reboot the system*
@reboot bash /home/user/Documentos/Start_script_so/script_connect_wifi.sh 
***Is executed at 09:16 am every day of month, every month and every day of week***
16 09 * * * bash /user/fernando/Documentos/Start_script_so/test.sh
