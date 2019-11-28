package practicagrailsenelaula

class BootStrap {

    def springSecurityService

    def init = { servletContext ->
        //Creando el asuario admin.

        Usuario admin =new Usuario(username: "admin", password: springSecurityService.encodePassword("admin"), email: "admin@admin.com").save(failOnError: true)
        Usuario usuario =new Usuario(username: "usuario", password: springSecurityService.encodePassword("admin"), email: "usuario@admin.com").save(failOnError: true)

        //Creando los roles.
        def roleAdmin = new Roles(authority: "ROLE_ADMIN") .save(failOnError: true)
        def roleUsuario = new Roles(authority: "ROLE_USUARIO").save(failOnError: true);

        //Asociando el usuario y los roles.
        UsuarioRoles.create(admin, roleAdmin);
        UsuarioRoles.create(usuario, roleUsuario);

    }
    def destroy = {
    }
}
