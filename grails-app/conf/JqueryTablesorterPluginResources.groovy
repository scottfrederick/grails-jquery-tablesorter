def appCtx = org.codehaus.groovy.grails.commons.ApplicationHolder.application.mainContext
def plugin = appCtx.pluginManager.getGrailsPlugin('jquery-tablesorter')
def jqtsver = plugin.instance.JQUERY_TABLESORTER_VERSION

modules = {
    'jquery-tablesorter-global' {
        resource id: 'js', url: [plugin: 'jqueryTablesorter', dir: 'js', file: "grails-tablesorter.js"]
    }

    'jquery-tablesorter' {
        dependsOn 'jquery', 'jquery-tablesorter-global'

        resource id: 'js', url: [plugin: 'jqueryTablesorter', dir: 'js/jquery-tablesorter', file: "jquery.tablesorter-${jqtsver}.min.js"],
                nominify: true
    }

    'jquery-tablesorter-dev' {
        dependsOn 'jquery', 'jquery-tablesorter-global'

        resource id: 'js', url: [plugin: 'jqueryTablesorter', dir: 'js/jquery-tablesorter', file: "jquery.tablesorter-${jqtsver}.js"]
    }
}