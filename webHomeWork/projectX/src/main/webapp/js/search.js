$(document).ready(function () {
    $('#search').on('click', function () {
        pushToServer();
    });

    $('#searchText').on('input', function () {
        pushToServer();
    });

    return false;
});

var pushToServer = function () {
    var searchText = $('#searchText');

    var dataToServer = {
        action: 'search',
        text: searchText.val()
    };

    if (dataToServer.text != '') {
        $.post('base', $.param(dataToServer), function (data) {
            var response = JSON.parse(data);
            if (response.action === 'found') {
                var s = "";
                for (var i = 0; i < response.docsName.length; i++)
                    s += response.docsName[i] + "\n" + response.textInDocs[i] + "\n" + "------------------------------" + "\n";
                $('#result').val(s);
            }
            else if(response.action === 'Not found')
                    $('#result').val("Not found");
                else
                    $('#result').val('Substitution package');
        });
    } else {
        $('#result').val("Text is empty");
    }
};