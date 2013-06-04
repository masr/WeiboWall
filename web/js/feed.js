/**
 * @author Abraham
 * Email:abraham1@163.com
 */
var latestId = -1;
function getHTML(feed){
    var rtn = "<li class='weibo'><span class='weibo_user'>" + feed.user + "</span> <span class='weibo_speak'></span><span class='weibo_content'>" + feed.content + "</span><li>";
    return rtn;
}


function dealFeed(text){
    var feeds = eval('(' + text + ')');
    var len = feeds.length;
    if (len >= 1) 
        latestId = feeds[len - 1].weibo_id;
    for (var i = 0; i < len; i++) {
        var html = $(getHTML(feeds[i]));
        $(".feedList").prepend(html);
        html.hide();
        html.slideDown('slow');
    }
    
    
    
}

function getFeed(){
    try {
        $.post('feed.php', {
            'latestId': latestId
        }, dealFeed);
    } 
    catch (e) {
        debug.print(e.message)
    };
    setTimeout(getFeed, 1000);
}




