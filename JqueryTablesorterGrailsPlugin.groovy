class JqueryTablesorterGrailsPlugin {
    // the plugin version
    def version = "2.0.5"

    static JQUERY_TABLESORTER_VERSION = "2.0.5"

    // the version or versions of Grails the plugin is designed for
    def grailsVersion = "1.3.7 > *"
    
    // the other plugins this plugin depends on
    def dependsOn = [jquery:'1.3.2 > *']

    // resources that are excluded from plugin packaging
    def pluginExcludes = [
    ]

    def author = "Scott Frederick"
    def authorEmail = "scottyfred@gmail.com"
    def title = "JQuery TableSorter Plugin"
    def description = '''\\
Integrates the JQuery TableSorter plugin to allow in-place sorting of HTML tables.
'''

    // URL to the plugin's documentation
    def documentation = "http://grails.org/plugin/jquery-tablesorter"

    def doWithSpring = {
    }

    def doWithApplicationContext = { applicationContext ->
    }
}
