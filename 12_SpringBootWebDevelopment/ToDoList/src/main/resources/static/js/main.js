$(function(){

    let cost_book_id = null;
    const appendBook = function(data){
        var bookCode = '<a href="#" class="book-link" data-id="' +
            data.id + '">' + data.name + '</a><br>';
        $('#book-list')
            .append('<div>' + bookCode + '</div>');
    };

    //Loading books on load page
//    $.get('/books/', function(response)
//    {
//        for(i in response) {
//            appendBook(response[i]);
//        }
//    });

    //Show adding book form
    $('#show-add-book-form').click(function(){
        $('#book-form').css('display', 'flex');
    });

    //Closing adding book form
    $('#book-form').click(function(event){
        if(event.target === this) {
            $(this).css('display', 'none');
        }
    });

    //Getting book
    $(document).on('click', '.book-link', function(){
        var link = $(this);
        var bookId = link.data('id');
        $.ajax({
            method: "GET",
            url: '/todos/' + bookId,
            success: function(response)
            {
                var code = '<span>Исполнитель:' + response.user + '</span>';
                link.parent().append(code);
            },
            error: function(response)
            {
                if(response.status == 404) {
                    alert('Книга не найдена!');
                }
            }
        });
        return false;
    });

    //Adding book
    $('#save-book').click(function()
    {
        var data = $('#book-form form').serialize();
        $.ajax({
            method: "POST",
            url: '/todos/',
            data: data,
            success: function(response)
            {
                $('#book-form').css('display', 'none');
                var book = {};
                book.id = response;
                var dataArray = $('#book-form form').serializeArray();
                for(i in dataArray) {
                    book[dataArray[i]['name']] = dataArray[i]['value'];
                }
                appendBook(book);
            }
        });
        return false;
    });
    //Editing book
    $('#edit-book').click(function()
    {
        var data = $('#book-form form').serialize();
        var link = $(this);

        data =  "id=" + cost_book_id + "&" + data;

        $.ajax({

            //contentType: 'application/X-Content-Type-Options',
            url: '/todos/' + cost_book_id,
            method: 'PUT',
            data: data,
           success: function(response)
            {
                console.log(response);
            },
            error: function(request,response,msg,error)
            {
                console.log(response);
            }
        });
        return false;
    });
    //Edit
    $(document).on('click', '.book-edit', function(){
        $('#book-form').css('display', 'flex');
        var stemp = $(this);
        cost_book_id = stemp.attr("data-id");
        //alert('Книга ! ' + stemp.attr("user") + stemp.attr("dateStart"));
        $([name = 'name']).val('Ogogo');
    });
    //Deleting
    $(document).on('click', '.book-delete', function(){
        var link = $(this);
        var bookId = link.data('id');
        alert('Книга удалена! ' + bookId);
        $.ajax({
            method: "DELETE",
            url: '/todos/' + bookId,
            success: function(response)
            {
                alert('Книга удалена!');
            },
            error: function(response)
            {
                alert('Книга не удалена!');
            }
        });
        return false;
    });
});