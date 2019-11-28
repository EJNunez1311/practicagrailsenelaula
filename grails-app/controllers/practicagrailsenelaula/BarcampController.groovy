package practicagrailsenelaula

class BarcampController {

    def index() { }

    def formulario() {

    }
    def save(){
        def respuesta = new Preguntas(params.Preguntas)
        respuesta.save()
        render "Success!"
    }
}
