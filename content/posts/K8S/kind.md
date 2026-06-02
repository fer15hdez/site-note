# KinD (kubernetes in docker)
- kind ejecuta los componentes de Kubernetes dentro de contenedores Docker dentro del contenedor que simula el cluster.  
- Las imagenes docker que se van a usar en kind se deben importar a kind para que el cluster pueda tener acceso.  

## Instal kind (kubernetes in docker)
- `go install sigs.k8s.io/kind@v0.30.0`: si tiene instaldo go.  

## Crear cluster con kind
- `kind create cluster --name cluster.test`: crea un cluster con el nombre "cluster.test". 
- `kind create cluster --config kind-config.yaml`: con un archivo.  
- `kind create cluster --name cluster.test --config kind-config.yaml`: crea un cluster con el nombre "cluster.test" con un archivo.  

## Listar clusters
- `kind get clusters`

## Loading an Image Into Your Cluster
- `kind load docker-image my-custom-image:unique-tag --name nameCluster`

## Listar imagenes dentro del cluster
- `crictl images` o `docker images`

## Listar contenedores dentro del cluster
- `crictl ps`