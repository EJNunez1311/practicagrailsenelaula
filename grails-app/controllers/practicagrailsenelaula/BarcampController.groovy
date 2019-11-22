package practicagrailsenelaula

class BarcampController {

    def index() { }

    def formulario() {
    }
    def save(){
        def estudiante = new Estudiante(params)
        estudiante.save()
        render "Success!"
    }
}
