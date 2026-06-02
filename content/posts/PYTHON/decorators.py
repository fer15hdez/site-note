def mi_decorador(funcion):
    def nueva_funcion(a, b):
        print("Se va a llamar")
        c = funcion(a, b)
        print("Se ha llamado")
        return c
    return nueva_funcion

@mi_decorador
def suma(a, b):
    print("Entra en funcion suma")
    return a + b

# suma(5,8)

# Se va a llamar
# Entra en funcion suma
# Se ha llamado

# autenticado = True
autenticado = False

def requiere_autenticación(f):
    def funcion_decorada(*args, **kwargs):
        if not autenticado:
            print("Error. El usuario no se ha autenticado")
        else:
            return f(*args, **kwargs)
    return funcion_decorada

@requiere_autenticación
def di_hola():
    print("Hola")
    
# di_hola()

# -----
def measure_time(function):
    def wrapper(*args, **kwargs):
        import time

        start = time.time()
        result = function(*args, **kwargs)
        total = time.time() - start
        print('Tiempo de ejecucion: ', total, 'seconds' )
        return result

    return wrapper


@measure_time
def suma(a, b):
    import time
    time.sleep(1)
    return 'Suma: ' + str(a + b)

# print(suma(10, 20))

# --------------------------------------------------------
def my_decorator_name(name):
    def my_custome_decorator(function):
        def wrapper(*args, **kwargs):

            print('Name:', name)
            return function(*args, **kwargs)

        return wrapper

    return my_custome_decorator

@my_decorator_name('CodigoFácilito')
def suma(a, b):
    return a + b

suma(10, 20)

