### descargar un sitio completo
    -r, --recursive: Para activar la descarga recursiva. La profundidad máxima predeterminada es 5.
    -l, --level=profundidad: Especifica la profundidad máxima de recursión.
    -k, --convert-links: Convierte los enlaces del documento para que sean adecuados para visualización local.
    -p, --page-requisites: Hace que Wget descargue todos los archivos necesarios para mostrar correctamente una página HTML.

wget -r -l 10 -k -p https://www.gnu.org 
