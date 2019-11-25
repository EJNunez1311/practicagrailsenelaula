package practicagrailsenelaula

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode(includes='username')
@ToString(includes='username', includeNames=true, includePackage=false)
class Estudiante implements Serializable {

    private static final long serialVersionUID = 1

    transient springSecurityService

    String nombre
    String email
    String username
    String password
    String respuesta1
    String respuesta2
    String respuesta3
    String respuesta4

    static constraints = {
        password blank: false, password: true
        username blank: false, unique: true
        email(email: true)
        respuesta1 blank: false
        respuesta2 blank: false
        respuesta3 blank: false
        respuesta4 blank: false
    }
    static transients = ['springSecurityService']
    static mapping = {
        table 'estudiante'
    }
}
