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
        'docs/**',
        'src/docs/**'
    ]

    def author = "Scott Frederick"
    def authorEmail = "scottyfred@gmail.com"
    def title = "JQuery TableSorter Plugin"
    def description = '''\\
Integrates the JQuery TableSorter plugin to allow in-place sorting of HTML tables.
'''

    def license = 'Apache'
    def documentation = "http://grails.org/plugin/jquery-tablesorter"
    def issueManagement = [ system: 'GitHub', url: 'https://github.com/scottfrederick/grails-jquery-tablesorter/issues' ]
    def scm = [ url: 'https://github.com/scottfrederick/grails-jquery-tablesorter' ]

    def doWithSpring = {
    }

    def doWithApplicationContext = { applicationContext ->
    }
}
