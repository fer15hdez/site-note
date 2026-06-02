# Kubernetes

- En YAML, si repites una clave al mismo nivel, la segunda suele sobrescribir a la primera.  
  ```yml
   spec:
      ingressClassName: nginx
   spec: # <--- ¡Aquí está el error! Debe haber un solo 'spec'
      rules:        
  ```

## Comandos
 -  kubectl apply -f <nombre_del_archivo.yaml> : aplicar manifiesto(archivo).  

## Deployments
- Los nombres de los objetos (como el name en metadata) deben seguir las reglas de DNS. No se permiten guiones bajos (_). Debes usar guiones medios (-).  
- Las más comunes son: 
   - app: Nombre de la aplicación.  
   - version: Para distinguir entre v1, v2, etc.  
   - env: Entorno (dev, staging, prod).  
   - tier: Capa (frontend, backend, db).  

   managed-by: Quién lo creó (helm, terraform).
- Etiquetas (Labels): Son pares clave-valor (metadatos) que se adjuntan a cualquier objeto de K8s (Pods, Nodos, etc.). Las etiquetas se usan para organizar y seleccionar grupos de objetos. Por ejemplo: app: frontend o env: dev.  
- Selectores (Selectors): Es una expresión que se usa para encontrar un subconjunto de objetos que coinciden con una etiqueta específica. Por ejemplo, un servicio (Service) usa un selector para saber a qué Pods debe dirigir el tráfico.  
- El contenido de spec.selector.matchLabels debe ser un subconjunto exacto de lo que pongas en spec.template.metadata.labels. Si no coinciden letra por letra, el Deployment no podrá "adueñarse" de los Pods que él mismo crea.  

## StatefulSet
- Es un Workload API object (un objeto de carga de trabajo), hermano del Deployment. Mientras que el Deployment es para aplicaciones "sin estado" (como un frontend que puedes borrar y recrear sin problemas), el `StatefulSet` es para aplicaciones "con estado" (como Postgres) que necesitan que su disco siempre sea el mismo aunque el Pod se mueva de nodo.   
- Es el estándar para bases de datos. Garantiza que cada Pod tenga su propio nombre y su propio disco persistente de forma ordenada.  
- Garantiza que el almacenamiento y la identidad de la red sean estables.  
- Para que un StatefulSet funcione correctamente, Kubernetes recomienda crear primero un Service de tipo "Headless" (sin IP propia) para dar identidad a los Pods.  

## PV (Persistent Volume)
- Es el recurso físico (un pedazo de disco en el nodo, un disco en la nube, etc.).  

## PVC (Persistent Volume Claim)
- Es un tipo de manifiesto.  
- Es la "Solicitud de Alquiler". El desarrollador dice: "Necesito 1GB de espacio con lectura/escritura". Kubernetes busca un PV que encaje y los "une" (Bound).  

## Service
- El Service es un objeto fundamental que actúa como un enlace permanente y un balanceador de carga para un grupo de Pods.  
- El Service toma la lista de direcciones IP de todos los Pods que lo respaldan y presenta una única IP virtual (ClusterIP) y un nombre DNS estable a otros Pods o servicios dentro del clúster.  

### Tipos de service
- `ClusterIP`: 
   - Uso Principal:	Comunicación interna entre aplicaciones dentro del mismo clúster de K8s.  
   - Funcionamiento: Proporciona una IP virtual dentro del clúster (la ClusterIP).  
   - Accesibilidad:	Solo se puede acceder a este Service desde dentro del clúster.  
   - Ejemplo: Un frontend necesita comunicarse con un backend o una base de datos.  
- `NodePort`: Abre un puerto estático en todos los nodos (Workers). El tráfico se dirige al puerto del nodo: `IP_del_Nodo`:`Puerto_del_Nodo`. Cada nodo redirige internamente al Service.  
   - Uso Principal: Exponer un Service al exterior mediante un puerto específico en cada nodo del clúster.  
   - Funcionamiento: Además de una ClusterIP, abre un puerto estático (el NodePort) en el rango por defecto (30000-32767) en todos los nodos del clúster.  
   - Accesibilidad: Se puede acceder al Service desde fuera del clúster a través de: http://<IP_del_Nodo>:<NodePort>.  
   - 
- `LoadBalancer`: Provisiona un balanceador de carga externo. El Service solicita una IP pública y estable al proveedor de la nube (AWS, GCP, Azure), que distribuye el tráfico entre los nodos.  

### Endpoints
- Este es el concepto "detrás de cámaras" de un Service.
- Cuando creas un Service, este busca Pods con las etiquetas (labels) correctas. Pero el Service no manda el tráfico "al aire"; mantiene una lista interna de las direcciones IP de los Pods que están listos. Esa lista se llama Endpoints.
  - Si el `Readiness Probe` tiene éxito: La IP del Pod aparece en la lista de Endpoints.
  - Si el `Readiness Probe` falla: La IP del Pod se elimina de la lista de Endpoints automáticamente.
  #### Comando
   - `kubectl get endpoints {nombre-service}`: te permite ver qué IPs están "activas" para recibir tráfico en un servicio específico.  

## INGRESS
- Es un objeto de API que actúa como una "puerta de entrada" inteligente al clúster. Mientras que un Service se encarga de conectar tráfico a nivel de red (Capa 4 - TCP/UDP), el Ingress gestiona el acceso externo a los servicios a nivel de aplicación (Capa 7 - HTTP/HTTPS).  
- Componentes importantes:
  - Ingress Resource (El Recurso): Es un archivo YAML donde defines las reglas de enrutamiento (ej: "si el tráfico viene a myapp.com/api, envíalo al Service A").  
  - Ingress Controller (El Controlador): Es el software que realmente ejecuta esas reglas. A diferencia de otros controladores en K8s, este no viene instalado por defecto. Debes elegir e instalar uno (como Nginx, Traefik, HAProxy o Kong).  
- Funcionalidades clave:     
   - Enrutamiento Basado en el Host (Host-based Routing). Puedes dirigir el tráfico según el nombre de dominio.    
     - ventas.empresa.com -> Service de Ventas.   
     - rrhh.empresa.com -> Service de Recursos Humanos.   
   - Enrutamiento Basado en la Ruta (Path-based Routing). Puedes dirigir el tráfico según la subcarpeta o ruta de la URL.  
     - empresa.com/pago -> Service de Pagos.  
     - empresa.com/carrito -> Service de Carrito.  
   - Terminación TLS/SSL
     - El Ingress puede gestionar tus certificados de seguridad. En lugar de configurar el certificado SSL en cada Pod o Service, lo configuras una sola vez en el Ingress. El tráfico llega cifrado al Ingress y luego viaja de forma "plana" (HTTP) dentro del clúster hacia los servicios.  

## Pods
- IPs Volátiles: La dirección IP de un Pod es efímera. Cuando un Pod muere y es reemplazado por uno nuevo, incluso si tienen el mismo nombre y etiquetas, el nuevo Pod tendrá una IP diferente.  

### Comandos
- `kubectl get nodes -o wide`: Encontrar la IP del nodo.  
- `kubectl get pods -o wide`:  Encontrar la IP del pod.  
- `kubectl get service <nombre-del-servicio> -o yaml`: Obtener los puertos si el servicio es de tipo NodePort.  
- `kubectl describe pod <nombre-del-pod>`:  Ver detalles de la IP.  
- `kubectl logs <nombre-del-pod>`: Ver los logs del contenedor y verificar si hay información sobre los puerto.  
- `kubectl logs -n ingress-nginx -l app.kubernetes.io/name=ingress-nginx --tail 10`: is used to retrieve the last 10 lines of logs from the NGINX Ingress Controller pods.  
- `kubectl delete pod <pod-name>`: Eliminar un pod.  
- `kubectl delete pods -l <etiqueta>=<valor>`: Eliminar los pods que tienen la <etiqueta>=<valor>.  
- `kubectl delete pods -n <espacio-de-nombres>`: Eliminar los pods por namespace.  
### Interactuar dentro de un Pods
- `kubectl exec -it <nombre-del-pod> -- /bin/sh`
- `kubectl exec -it <nombre-del-pod> -- bash`

## Namespace
### Comandos
- `kubectl.exe get namespace`: Lista los namespace.   

### Comados deployments
- `kubectl get deployments`: Lista los deployments.  
- `kubectl get deploy`: Lista más concisa.  
- `kubectl rollout status deployment/<nombre-del-deployment>`: Para ver el estado de un deployment específico.  
- `kubectl describe deployment/<nombre-del-deployment>`: Muestra los valores del deployment.  
- `kubectl rollout restart deployment <nombre-del-deployment>`: inicia un reinicio progresivo de los pods, creando nuevos pods y terminando los antiguos de manera controlada, según la estrategia de reinicio de la implementación. 



## Jobs
### Comandos
- `kubectl delete job <nombre-del-job>`: Elimina el Job y sus Pods (comportamiento en cascada).  
- `kubectl delete job <nombre-del-job> -n <namespace>`: Para un Job en un namespace específico.  
- Si creaste el Job con un archivo YAML, usa `kubectl delete -f <archivo.yaml>`.  
- `kubectl get jobs`: Listar todos los Jobs.  
- `kubectl get jobs -n produccion`: Listar Jobs en un namespace específico (ej. produccion).  
- `kubectl get jobs --show-labels`: Listar Jobs y sus Pods relacionados (con más detalle).  
- `kubectl describe job <nombre-del-job>`: Ver detalles de un Job específico.  
- `kubectl get pods --selector=job-name=<nombre-del-job>`: Ver los Pods que ha creado un Job.  
- `kubectl logs <nombre-del-pod-del-job>`: Ver los logs de un Job (necesitas el nombre del pod).  
- ``

## CronJobs 
### Comandos
- `kubectl get cronjobs`: Listar todos los CronJobs.  
- `kubectl get cronjobs -n <nombre-del-namespace>`: Listar CronJobs en un namespace específico.  
- `kubectl describe cronjob <nombre-del-cronjob>`: Ver detalles de un CronJob.  
- `kubectl get jobs --selector=job-name=<nombre-del-cronjob>`: Ver los Jobs creados por un CronJob. 

## ConfigMap y Secret
- Se puede hacer dos configuraciones: Mediante variables de entorno o montando un volumen.  
- En el deployment es donde se referencia la configuracion del configMap.  
- Los ConfigMaps y Secrets son objetos de un Namespace. Un Pod en el namespace A no puede consumir un Secret del namespace B de forma directa.  
- Referencia antes que ejecución: El Pod debe referenciar el nombre exacto del objeto. Si el Pod intenta arrancar y el ConfigMap/Secret referenciado no existe, el Pod quedará en estado CreateContainerConfigError.  
- Inmutabilidad: Puedes marcar un ConfigMap/Secret como immutable: true. Esto impide cambios accidentales y mejora el rendimiento del clúster (el kubelet deja de vigilar cambios).  
- Actualización de Variables de Entorno: Si usas variables de entorno y cambias el ConfigMap, el Pod NO verá el cambio hasta que se reinicie. Si usas volúmenes, el cambio es eventual.  

### Comandos
- `kubectl get configmap <nombre_configMap>`: Muestra el configMap especificado por nombre.  

## Secret
- En el deployment es donde se referencia la configuracion del configMap.  

### Comandos
- `kubectl get secret <nombre_secret>`: Muestra el secret especificado por nombre.  

## Probes (Sondas)
- En Kubernetes, el Kubelet usa Probes (Sondas) para preguntarle al contenedor: "¿Cómo estás?".  
- LivenessProbe (Sonda de Vida) 🩺  
  - Pregunta: "¿Sigues vivo o te has quedado bloqueado (deadlock)?"  
  - Acción si falla: Kubernetes mata el contenedor y crea uno nuevo (reinicio).  
  - Uso: Para aplicaciones que se quedan "congeladas" pero el proceso sigue figurando como activo.

- ReadinessProbe (Sonda de Disponibilidad) 🚦
  - Pregunta: "¿Estás listo para recibir clientes (tráfico)?"
  - Acción si falla: Kubernetes no mata el contenedor, pero le dice al Service: "Oye, no le mandes tráfico a este Pod, todavía está cargando datos o está saturado".
  - Uso: Para apps que tardan en cargar una base de datos al inicio o que están temporalmente sobrecargadas.  