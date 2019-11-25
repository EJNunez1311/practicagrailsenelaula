package practicagrailsenelaula

class BarcampController {

    def index() { }

    def formulario() {

    }
    def save(){
        def estudiante = new Estudiante(params.Estudiante)
        render"Hola la respuesta es: "+ estudiante.respuesta1 + estudiante.respuesta2
        estudiante.save()
        render "Success!"
    }
}
