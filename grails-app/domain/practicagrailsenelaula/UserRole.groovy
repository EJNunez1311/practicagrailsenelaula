package practicagrailsenelaula

import grails.gorm.DetachedCriteria
import groovy.transform.ToString

import org.apache.commons.lang.builder.HashCodeBuilder

@ToString(cache=true, includeNames=true, includePackage=false)
class UsuarioRoles implements Serializable {

    private static final long serialVersionUID = 1

    Usuario usuario
    Roles roles

    @Override
    boolean equals(other) {
        if (other instanceof UsuarioRoles) {
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

    static UsuarioRoles get(long usuarioId, long rolesId) {
        criteriaFor(usuarioId, rolesId).get()
    }

    static boolean exists(long usuarioId, long rolesId) {
        criteriaFor(usuarioId, rolesId).count()
    }

    private static DetachedCriteria criteriaFor(long usuarioId, long rolesId) {
        UsuarioRoles.where {
            usuario == Usuario.load(usuarioId) &&
                    roles == Roles.load(rolesId)
        }
    }

    static UsuarioRoles create(Usuario usuario, Roles roles) {
        def instance = new UsuarioRoles(usuario: usuario, roles: roles)
        instance.save()
        instance
    }

    static boolean remove(Usuario u, Roles r) {
        if (u != null && r != null) {
            UsuarioRoles.where { usuario == u && roles == r }.deleteAll()
        }
    }

    static int removeAll(Usuario u) {
        u == null ? 0 : UsuarioRoles.where { usuario == u }.deleteAll()
    }

    static int removeAll(Roles r) {
        r == null ? 0 : UsuarioRoles.where { roles == r }.deleteAll()
    }

    static constraints = {
        roles validator: { Roles r, UsuarioRoles ur ->
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