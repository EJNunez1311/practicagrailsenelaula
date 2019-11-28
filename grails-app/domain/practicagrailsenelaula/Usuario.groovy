package practicagrailsenelaula

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode(includes='username')
@ToString(includes='username', includeNames=true, includePackage=false)
class Usuario implements Serializable {

    private static final long serialVersionUID = 1

    transient springSecurityService

    String username
    String password
    Preguntas pregunta_id

    //Puedo poner otras propiedades.
    String email;

    Set<Roles> getAuthorities() {
        UsuarioRoles.findAllByUsuario(this)*.roles
    }

    def beforeInsert() {
        encodePassword()
    }

    def beforeUpdate() {
        if (this.isDirty('password')) { //TODO: verificar en en foro.
            encodePassword()
        }
    }

    protected void encodePassword() {
        password = springSecurityService?.passwordEncoder ? springSecurityService.encodePassword(password) : password
    }

    static transients = ['springSecurityService']

    static constraints = {
        password blank: false, password: true
        username blank: false, unique: true
        email(email: true)
        pregunta_id(nullable: false)
    }

    static mapping = {
        password column: '`password`'
    }
}