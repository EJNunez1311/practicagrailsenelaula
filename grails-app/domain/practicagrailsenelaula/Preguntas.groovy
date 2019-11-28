package practicagrailsenelaula

class Preguntas {

    private static final long serialVersionUID = 1
    Usuario usuario_id
    String respuesta1
    String respuesta2
    String respuesta3
    String respuesta4

    static constraints = {
        respuesta1 blank: false
        respuesta2 blank: false
        respuesta3 blank: false
        respuesta4 blank: false
    }
}
