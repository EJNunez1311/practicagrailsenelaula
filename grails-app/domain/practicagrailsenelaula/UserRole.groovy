package practicagrailsenelaula

import grails.gorm.DetachedCriteria
import groovy.transform.ToString
import org.apache.commons.lang.builder.HashCodeBuilder

@ToString(cache=true, includeNames=true, includePackage=false)
class UserRole implements Serializable {
    private static final long serialVersionUID = 1

    Estudiante usuario
    Roles roles

    @Override
    boolean equals(other) {
        if (other instanceof UserRole) {
            other.usuarioId == usuario?.id && other.rolesId == roles?.id
        }
    }

    @Override
    int hashCode() {
        def builder = new HashCodeBuilder()
        if (usuario) builder.append(usuario.id)
        if (roles) builder.append(roles.id)
        builder.toHashCode()
    }

    static UserRole get(long usuarioId, long rolesId) {
        criteriaFor(usuarioId, rolesId).get()
    }

    static boolean exists(long usuarioId, long rolesId) {
        criteriaFor(usuarioId, rolesId).count()
    }

    private static DetachedCriteria criteriaFor(long usuarioId, long rolesId) {
        UserRole.where {
            usuario == Usuario.load(usuarioId) &&
                    roles == Roles.load(rolesId)
        }
    }

    static UserRole create(Estudiante usuario, Roles roles) {
        def instance = new UserRole(usuario: usuario, roles: roles)
        instance.save()
        instance
    }

    static boolean remove(UserRole u, Roles r) {
        if (u != null && r != null) {
            UserRole.where { usuario == u && roles == r }.deleteAll()
        }
    }

    static int removeAll(Estudiante u) {
        u == null ? 0 : UserRole.where { usuario == u }.deleteAll()
    }

    static int removeAll(Roles r) {
        r == null ? 0 : UserRole.where { roles == r }.deleteAll()
    }

    static constraints = {
        roles validator: { Roles r, UserRole ur ->
            if (ur.usuario?.id) {
                UsuarioRoles.withNewSession {
                    if (UsuarioRoles.exists(ur.usuario.id, r.id)) {
                        return ['userRole.exists']
                    }
                }
            }
        }
    }

    static mapping = {
        id composite: ['usuario', 'roles']
        version false
    }
}
