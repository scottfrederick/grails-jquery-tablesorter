/* Copyright (c) 2011 Scott Frederick
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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