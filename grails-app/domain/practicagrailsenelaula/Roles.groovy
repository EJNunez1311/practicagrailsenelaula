package practicagrailsenelaula

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode(includes='authority')
@ToString(includes='authority', includeNames=true, includePackage=false)
class Roles {

    private static final long serialVersionUID = 1

    String authority
    static constraints = {
        authority blank: false, unique: true
    }
}
