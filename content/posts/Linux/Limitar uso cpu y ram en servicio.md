# Limitar uso recurso

## Snapd
`sudo systemctl edit snapd(este es el servicio)`
En el archivo override.conf que sale por defecto poner lo siguiente  

`[Service]`
`CPUQuota=20%`         # Límite de CPU al 20%  
`MemoryMax=512M`       # Límite de memoria a 512MB  
`MemorySwapMax=1G`     # Límite de swap a 1GB (opcional)  

### Recargar systemd y reiniciar el servicio:
`sudo systemctl daemon-reexec`    # Recarga la configuración de systemd
`sudo systemctl restart snapd`    # Reinicia el servicio `snapd`

### Verificar que los límites se han aplicado correctamente:
`systemctl show snapd | grep CPUQuota`      # Verifica el límite de CPU
`systemctl show snapd | grep -i memory`    # Verifica el límite de memoria

### Posible resultado
CPUQuota=20%          # CPU limitado al 20%
MemoryMax=536870912   # Memoria limitada a 512MB
MemorySwapMax=1073741824  # Swap limitado a 1GB (si lo pusiste)


## Desde el archivo de configuracion (CPU)
- Se modifica el valor para cada nucleo del cpu  
`echo 1200000 | sudo tee /sys/devices/system/cpu/cpu*/cpufreq/scaling_max_freq`

``` bash
MAX_FREQ=2800000 # Cambia este valor a la frecuencia deseada en kHz
for i in /sys/devices/system/cpu/cpu*/cpufreq/scaling_max_freq; do
    echo "$MAX_FREQ" | sudo tee "$i"
    echo "$i"
done
```
### Crear servicio 
- Para que se ejecute permanente

`sudo nano /etc/systemd/system/cpufreq-limit.service`
"
[Unit]
Description=Set CPU max frequency
After=multi-user.target

[Service]
ExecStart=/bin/bash -c "for i in /sys/devices/system/cpu/cpu*/cpufreq/scaling_max_freq; do echo frecuencia_deseada_KHZ | tee $i; done"

[Install]
WantedBy=multi-user.target
"
