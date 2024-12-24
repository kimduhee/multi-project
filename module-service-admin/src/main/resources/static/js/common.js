function getAjaxData(ajaxUrl, ajaxData, callback) {

    $.ajax({
        url: ajaxUrl,
        method: 'post',
        data : JSON.stringify(ajaxData),
        headers : {              // Http header
          //"Content-Type" : "application/json"
        },
        //dataType : 'json',
        success: function (data, status, xhr) {
            callback(data);
        },
        error: function (data, status, err) {
            alert(err);
        },
        complete: function () {
        }
    });
}