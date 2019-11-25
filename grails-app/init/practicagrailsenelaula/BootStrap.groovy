package practicagrailsenelaula

class BootStrap {

    def authenticateService

    def init = { servletContext ->
        //Creando los roles.
        def roleAdmin = new Roles(authority: "ROLE_ADMIN").save(failOnError: true)
        def roleUsuario = new Roles(authority: "ROLE_USUARIO").save(failOnError: true);

        //Creando el asuario admin.
        Estudiante admin = new Estudiante(name:"admin", username: "admin", password: springSecurityService.encodePassword("admin"), email: "admin@admin.com").save(failOnError: true)
        Estudiante usuario = new Estudiante(name:"usuario", username: "usuario", password: springSecurityService.encodePassword("1234"), email: "usuario@admin.com").save(failOnError: true)

        //Asociando el usuario y los roles.
        UserRole.create(admin, roleAdmin);
        UserRole.create(usuario, roleUsuario);
    }
    def destroy = {
    }
}
