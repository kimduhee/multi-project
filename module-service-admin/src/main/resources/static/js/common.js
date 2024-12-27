const commonJs = {
    getAjaxData: function(ajaxUrl, ajaxData, callback) {
        $.ajax({
                url: ajaxUrl,
                method: 'post',
                data : JSON.stringify(ajaxData),
                contentType : "application/json",
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
    },

    paging: function(totalPageCount, pageNo, pageId, fn) {

        if(totalPageCount == 0) {
            $("#"+pageId).html("");
            return false;
        }

        let pageBlock = 10;
        let blockNo = commonJs.toInt(pageNo/pageBlock) + 1;
        let startPageNo = ((blockNo - 1) * pageBlock) + 1;
        let endPageNo = blockNo * pageBlock;

        if(endPageNo > totalPageCount) {
            endPageNo = totalPageCount;
        }

        let prevBlockPageNo = (blockNo - 1) * pageBlock;
        let nextBlockPageNo = blockNo * pageBlock;

        let pageHTML = "<div class='col-sm-12 col-md-12'>";
        pageHTML += "    <div class='dataTables_paginate paging_simple_numbers'>";
        pageHTML += "        <ul class='pagination'>";

        //맨처음, 이전 페이지 활성화
        if(prevBlockPageNo > 0) {
            pageHTML += "<li class='paginate_button page-item previous'><a href=javascript:"+ fn +"(" + 1 + "); class='page-link'><<</a></li>";
            pageHTML += "<li class='paginate_button page-item previous'><a href=javascript:"+ fn +"(" + prevBlockPageNo + "); class='page-link'><</a></li>";
        }

        for(let i = startPageNo; i <= endPageNo; i++) {
            if(i == pageNo) {
                pageHTML += "<li class='paginate_button page-item active'><a href=javascript:void(0); class='page-link'>"+(i)+"</a></li>";
            } else {
                pageHTML += "<li class='paginate_button page-item'><a href=javascript:"+fn+"(" + (i) + "); class='page-link'>"+(i)+"</a></li>";
            }
        }

        //마지막, 다음페이지 활성화
        if(nextBlockPageNo  < totalPageCount) {
            pageHTML += "<li class='paginate_button page-item next'><a href=javascript:"+ fn +"(" + nextBlockPageNo  + "); class='page-link'>></a></li>";
            pageHTML += "<li class='paginate_button page-item next'><a href=javascript:"+ fn +"(" + (totalPageCount-1) + "); class='page-link'>>>/a></li>";
        }

        pageHTML += "        </ul>";
        pageHTML += "    </div>";
        pageHTML += "</div>";

        $("#"+pageId).html(pageHTML);
    },

    toInt: function(value) {
        if(value != null) {
            return parseInt(value, 10);
        }
    }
}
