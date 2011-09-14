$(document).ready(function() {
    $('.jqts-sortable').tablesorter({
        sortList: [[0,0]],
        cssAsc: 'asc',
        cssDesc: 'desc',
        cssHeader: 'sortable'
    });
    
    $('.jquery-sortable').bind('sortEnd', function() {
        $(this).find('>tbody >tr:even').removeClass('odd').addClass('even');
        $(this).find('>tbody >tr:odd').removeClass('even').addClass('odd');
    });
});