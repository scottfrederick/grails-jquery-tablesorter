import grails.util.Environment

class TableSorterTagLib {
    static namespace = "jqts"

    def pluginManager

    def tableSorterOptions = [
        'sortList': '',
        'sortForce': '',
        'headers': '',
    ]

    def tableSorterTextOptions = [
        'textExtraction': '',
        'cssAsc': 'asc',
        'cssDesc': 'desc',
        'cssHeader': 'sortable',
    ]

    def tagOptions = [
        'zebraStripe': 'true',
        'cssEven': 'even',
        'cssOdd': 'odd'
    ]

    def resources = { attrs ->
        def inDevMode = Environment.current == Environment.DEVELOPMENT

        if (pluginManager?.hasGrailsPlugin('resources')) {
            def moduleName = inDevMode ? 'jquery-tablesorter-dev' : 'jquery-tablesorter'
            out << r.require(module: moduleName)
        }
        else {
            def plugin = pluginManager.getGrailsPlugin('jquery-tablesorter')
            def version = plugin.instance.class.JQUERY_TABLESORTER_VERSION

            def minified = inDevMode ? '' : '.min'
            def fileName = "jquery.tablesorter-${version}${minified}.js"

            out << """<script src="${g.resource(plugin: 'jquery-tablesorter', dir: 'js/jquery-tablesorter', file: fileName).encodeAsHTML()}" type="text/javascript"></script>"""
        }
    }

    def sortableTable = { attrs, body ->
        def options = getOptions(attrs)

        if (!attrs.id) {
            throwTagError("Tag [sortableTable] is missing required attribute [id]")
        }

        writeTable(attrs, body)
        writeJavascript(attrs, options)
    }

    def sortableColumn = { attrs ->
        def title = getColumnTitle(attrs)

        out << "<th" + encodeAllAttrs(attrs) + ">"
        out << "<a href='javascript:void(0);'>${title}</a>"
        out << "</th>"
    }

    private def getColumnTitle(attrs) {
        if (!attrs.title && !attrs.titleKey) {
            throwTagError("Tag [sortableColumn] is missing required attribute [title] or [titleKey]")
        }

        def title = attrs.remove("title")
        def titleKey = attrs.remove("titleKey")
        if (titleKey) {
            if (!title)
                title = titleKey
            def messageSource = grailsAttributes.messageSource
            def locale = RCU.getLocale(request)
            title = messageSource.getMessage(titleKey, null, title, locale)
        }
        return title
    }

    private def getOptions(attrs) {
        def params = [:]
        tableSorterOptions.each {
            params[it.key] = attrs.remove(it.key) ?: it.value
        }
        tableSorterTextOptions.each {
            params[it.key] = attrs.remove(it.key) ?: it.value
        }
        tagOptions.each {
            params[it.key] = attrs.remove(it.key) ?: it.value
        }
        params
    }

    private void writeTable(attrs, body) {
        out << "<table" + encodeAllAttrs(attrs) + ">"
        out << body()
        out << "</table>"
    }

    private void writeJavascript(attrs, options) {
        if (pluginManager?.hasGrailsPlugin('resources')) {
            out << r.script {
                writeTablesorterJavascript(attrs, options)
            }
        }
        else {
            out << g.javascript {
                writeTablesorterJavascript(attrs, options)
            }
        }
    }

    private void writeTablesorterJavascript(attrs, options) {
        out << '$("#' + attrs.id.encodeAsJavaScript() + '").tablesorter({'
        writeTablesorterOptions(options)
        out << '});'

        if (!("false" == options.zebraStripe)) {
            def cssOdd = options.cssOdd
            def cssEven = options.cssEven

            out << '$("#' + attrs.id.encodeAsJavaScript() + '").bind("sortEnd", function() {'
            out << '$(this).find(">tbody >tr:even").removeClass("' + cssOdd + '").addClass("' + cssEven + '");'
            out << '$(this).find(">tbody >tr:odd").removeClass("' + cssEven + '").addClass("' + cssOdd + '");'
            out << '});'
        }
    }

    private void writeTablesorterOptions(options) {
        tableSorterOptions.each {
            if (options[it.key]) {
                out << "${it.key}:${options[it.key]},"
            }
        }
        tableSorterTextOptions.each {
            if (options[it.key]) {
                out << "${it.key}:'${options[it.key]}',"
            }
        }
    }

    private def encodeAllAttrs(attrs) {
        def string = ''
        attrs.collect { string += " ${it.key}='${it.value}'" }
        string
    }
}
