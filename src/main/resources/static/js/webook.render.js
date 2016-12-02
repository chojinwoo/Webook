var Book;
var nowCfi;
$.fn.bookRender = function(bookPath, bookMark, option) {
    Book = ePub(bookPath, {
        restore: true,
        reload: true,
        generatePagination: true,
        history: true
    });
    var $chapter = $('#'+option.chapter);
    var $viewer = this
    Book.renderTo($viewer.attr('id'));

    Book.generatePagination($viewer.width(), $viewer.height());
    Book.on('book:ready', function() {
        var spines = Book.spine;
        var lis = new Array();
        $.each(spines, function() {
            var id =this.id;
            var cfi = this.cfi;
            var li = '<li><a class="gotoChapter" data-cfi="'+cfi+'">'+id+'</a></li>';
            lis.push(li);
        })
        $chapter.append(lis.join(''));
        $('.gotoChapter').on('click', function() {
            var cfi = $(this).attr('data-cfi');
            Book.gotoCfi(cfi);
        })
    })

    Book.on('book:pageChanged', function (location) {
        $('#setting-page').attr('placeholder', location.anchorPage)
        var cfi = Book.pagination.cfiFromPage(location.anchorPage);
        var seq = $("#area").data('seq');
        $.ajax({
            url:'/bookMark',
            data:'books_seq='+seq+'&books_mark='+cfi,
            type:'post',
            success:function(data) {
                // alert(data);
            }, error:function(xhr, status, error) {
                alert('bookMark Err : '+ error)
            }
        })
    });

    Book.on('renderer:locationChanged', function (locationCfi) {
        nowCfi = locationCfi;
        $('.book-title, title').text(Book.metadata.bookTitle + ' - ' + Book.currentChapter.id);
    });
}