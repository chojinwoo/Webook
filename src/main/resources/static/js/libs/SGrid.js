var SGrid = function () {
    var DATA_KEY = 'bs.SGrid'
    var EVENT_KEY = DATA_KEY + ".";
    var GRID_COUNT = 1;
    var TITLE_HEIGHT = 40;
    var VERSION = '1.0.0';

    var DEFAULTS = {
        width: 500,
        height: 500,
        check: true,
        count: true,
        title: '',
        dataRepo:'local',
        btn: {upd: false, ins: false, del: false, find: true, refresh: true, excel: false}
    }

    var SGrid = function () {

        function SGrid(element, option) {
            var cnt = GRID_COUNT;
            this.init_data(option);
            this.init(element, option);
            this.btnEvent(element, option, cnt);
            this.callbackEvent(element, option, cnt);
            this.toggleEvent(element,cnt)
            this.checkEvent(cnt);
            if (option.resize)this.resizeEvent(element, option);
            if(option.lazy)this.lazyEvent(option, cnt);
            GRID_COUNT++;
        }

        SGrid.prototype.init_data = function (option) {
            if(!option.NOWDATA) {
                if (option.width == 'auto')option.width = $(window).width() - 25;
                option.ORIDATA = [];
                if (option.dataRepo == 'local') {
                    option.ORIDATA = option.data;
                    option.NOWDATA = option.ORIDATA;
                }
                if (option.dataRepo == 'http') {

                    $.ajax({
                        url: option.data,
                        type: 'post',
                        dataType: 'json',
                        async: false,
                        success: function (data) {
                            option.ORIDATA = data;
                            option.NOWDATA = data;
                        }, error: function (xhr, status, error) {
                            alert("Json load Error : " + error);
                        }
                    })
                }
            }
            var tot_width = 0;
            for (var i in option.header) {
                var surplus = 0;
                if (option.colsType[i] != 'hide')tot_width += option.colsWidth[i];
            }

            surplus = option.width - tot_width;
            if (option.check)surplus -= 40;
            if (option.count)surplus -= 40;
            option.surplus = surplus;
        }

        SGrid.prototype.init = function (element, option) {
            var cnt = GRID_COUNT;
            var table = new Array();
            table.push('<div class="SGrid" style="height:' + option.height + 'px; width:' + option.width + 'px;">')
            table.push('<table id="SGrid-table' + cnt + '" class="SGrid-table">');
            table.push(this.init_header(option));
            table.push(this.init_body(option));
            table.push(this.init_foot(option));
            table.push('</table>')
            table.push('</div>')

            $(element).css('position', 'relative').append('<div style="width:' + option.width + 'px" id="SGrid-title' + cnt + '" class="SGrid-title" style="height:' + TITLE_HEIGHT + 'px"><strong>' + option.title + '</strong><i class="fa fa-chevron-circle-down SGrid-toggle" id="SGrid-toggle' + cnt + '"></i></div>')
            $(element).append(table.join(''));
        }

        SGrid.prototype.lazyEvent = function(option, cnt) {
            var old_scroll = 0;
            $('#SGrid-table'+cnt).closest('.SGrid').scroll(function() {
                var new_scroll = $(this).scrollTop()
                var s = $('#SGrid-table'+cnt).find('tbody tr:last').index() + 1;
                var e = s+ 5;
                if(option.NOWDATA.length <= e) {e = option.NOWDATA.length;}
                if(old_scroll < new_scroll) {
                    var body = new Array()
                    for(var i=s; i<e; i++) {
                        body.push('<tr>')
                        if (option.count)body.push('<td class="SGrid-state">' + (Number(i) + 1) + '</td>')
                        if (option.check)body.push('<td class="SGrid-check"><input type="checkbox"/></td>')
                        var tot_width = 0;
                        for (var ii = 0; ii < option.cols.length; ii++) {
                            var surplus = 0;
                            if (option.colsType[ii] != 'hide')tot_width += option.colsWidth[ii];
                            if (option.colsWidth.length == Number(ii) + 1) {
                                surplus = option.width - tot_width;
                                if (option.check)surplus -= 40;
                                if (option.count)surplus -= 40;
                            }
                            option.surplus = surplus;
                            if (option.colsType[ii] == 'hide')body.push('<td style="text-align:' + option.colsAlign[ii] + ';width:' + option.colsWidth[ii] + 'px; display:none" col="' + option.cols[ii] + '">' + option.NOWDATA[i][option.cols[ii]] + '</td>')
                            if (option.colsType[ii] == 'text')body.push('<td style="text-align:' + option.colsAlign[ii] + ';width:' + option.colsWidth[ii] + 'px;" col="' + option.cols[ii] + '">' + option.NOWDATA[i][option.cols[ii]] + '</td>')
                            if (typeof option.colsType[ii] == 'object') {
                                body.push('<td style="text-align:' + option.colsAlign[ii] + ';width:' + option.colsWidth[ii] + 'px;" col="' + option.cols[ii] + '" data-select-cd="'+option.NOWDATA[i][option.cols[ii]]+'">' + option.colsType[ii].select[option.NOWDATA[i][option.cols[ii]]] + '</td>');
                            }
                            if (option.colsType[ii] == 'checkbox') {
                                var checked = '';
                                if (option.NOWDATA[i][option.cols[ii]])checked = 'checked';
                                body.push('<td style="text-align:' + option.colsAlign[ii] + ';width:' + option.colsWidth[ii] + 'px;"><input name="' + option.cols[ii] + '" col="' + option.cols[ii] + '" type="checkbox" ' + checked + ' disabled></td>')
                            }
                        }
                        if (option.surplus > 0)body.push('<td style="width:' + option.surplus + 'px"></td>')
                        body.push('</tr>')
                    }
                    $('#SGrid-table'+cnt).find('tbody').append(body.join(''));


                }
                old_scroll = $(this).scrollTop();
            })
        }

        SGrid.prototype.resizeEvent = function (element, option) {
            $(window).resize(function () {
                var width = $(window).width() - 20;
                option.width = width;
                SGrid.prototype.init_data(option);
                $(element).find('.SGrid-title').css('width', width+'px')
                $(element).find('.SGrid').css('width', width+'px')
                $(element).find('tfoot').css('width', width+'px')
                $(element).find('tbody').css('width', width+'px')
                $(element).find('thead').css('width', width+'px')
                $(element).find('thead tr:first-child th:last-child').css('width', option.surplus+'px');
                $(element).find('tbody tr td:last-child').css('width', option.surplus+'px');
            })
        }

        SGrid.prototype.reMapping = function (element, option) {
            new SGrid(element, option)
        }

        SGrid.prototype.reFresh = function (option, cnt) {
            $('#SGrid-table' + cnt).find('tbody').remove();
            $('#SGrid-table' + cnt).find('thead').after(this.init_body(option));
            $('#SGrid-table' + cnt).find('tbody tr:first-child td').css('padding-top', $('#SGrid-table' + cnt).find('thead').height() + 'px');
            $('#SGrid-table'+cnt).closest('.SGrid').scrollTop(0);
        }

        SGrid.prototype.toggleEvent = function (_this, cnt) {
            $(_this).find('#SGrid-toggle' + cnt).click(function () {
                if ($(_this).find('#SGrid-table' + cnt).css('display') != 'none') {
                    $(this).removeClass('fa-chevron-circle-down').addClass('fa-chevron-circle-up');
                    $(_this).find('#SGrid-table' + cnt).fadeOut('slow');
                    $(_this).find('.SGrid').fadeOut('slow');
                    $(this).parent().addClass('SGrid-title-active');
                } else {
                    $(this).parent().removeClass('SGrid-title-active');
                    $(this).removeClass('fa-chevron-circle-up').addClass('fa-chevron-circle-down');
                    $(_this).find('#SGrid-table' + cnt).fadeIn('slow');
                    $(_this).find('.SGrid').fadeIn('slow');
                }

            })
        }

        SGrid.prototype.checkEvent = function (cnt) {
            $(document).on('change', '#SGrid-table' + cnt + ' .SGrid-check input', function () {
                var tr = $(this).closest('tr');
                var state = tr.find('.SGrid-state').text();
                if (state == 'U')tr.empty().html(tr.data('ori_data'));
                if (state == 'I')tr.remove();
                if (state != 'U' && state != 'I' && state != 'D') {
                    tr.data('seq', state);
                    tr.find('.SGrid-state').text('D').css('color', 'red');
                }
                if (state == 'D')tr.find('.SGrid-state').text(tr.data('seq')).css('color', '')
            })
        }

        SGrid.prototype.init_header = function (option) {
            var header = new Array();
            header.push('<thead class="SGrid-header" style="width:' + option.width + 'px">')
            header.push('<tr>')
            if (option.count)header.push('<th class="SGrid-state">No</th>')
            if (option.check)header.push('<th class="SGrid-allCheck"><input type="checkbox"/></th>')
            for (var i in option.header) {
                var display = '';
                if (option.colsType[i] == 'hide')display = 'display:none;'
                header.push('<th style="width:' + option.colsWidth[i] + 'px;' + display + '">' + option.header[i] + '</th>')
            }

            header.push('<th style="width:' + option.surplus + 'px"></th>')
            header.push('</tr>')
            header.push('</thead>')
            return header.join('');
        }

        SGrid.prototype.init_body = function (option) {
            var body = new Array();
            body.push('<tbody class="SGrid-body" style="width:' + option.width + 'px">')
            var dataLength = option.NOWDATA.length;
            if(option.lazy && Math.round(option.height /20) < dataLength)dataLength = Math.round(option.height /20);
            for(var i=0; i<dataLength; i++) {
                body.push('<tr>')
                if (option.count)body.push('<td class="SGrid-state">' + (Number(i) + 1) + '</td>')
                if (option.check)body.push('<td class="SGrid-check"><input type="checkbox"/></td>')
                var tot_width = 0;
                for (var ii = 0; ii < option.cols.length; ii++) {
                    var surplus = 0;
                    if (option.colsType[ii] != 'hide')tot_width += option.colsWidth[ii];
                    if (option.colsWidth.length <= (Number(ii) + 1)) {
                        surplus = option.width - tot_width;
                        if (option.check)surplus -= 40;
                        if (option.count)surplus -= 40;

                        option.surplus = surplus;
                    }
                    if (option.colsType[ii] == 'hide')body.push('<td style="text-align:' + option.colsAlign[ii] + ';width:' + option.colsWidth[ii] + 'px; display:none" col="' + option.cols[ii] + '">' + option.NOWDATA[i][option.cols[ii]] + '</td>')
                    if (option.colsType[ii] == 'text')body.push('<td style="text-align:' + option.colsAlign[ii] + ';width:' + option.colsWidth[ii] + 'px;" col="' + option.cols[ii] + '">' + option.NOWDATA[i][option.cols[ii]] + '</td>')
                    if (typeof option.colsType[ii] == 'object') {
                        body.push('<td style="text-align:' + option.colsAlign[ii] + ';width:' + option.colsWidth[ii] + 'px;" col="' + option.cols[ii] + '" data-select-cd="'+option.NOWDATA[i][option.cols[ii]]+'">' + option.colsType[ii].select[option.NOWDATA[i][option.cols[ii]]] + '</td>');
                    }
                    if (option.colsType[ii] == 'checkbox') {
                        var checked = '';
                        if (option.NOWDATA[i][option.cols[ii]])checked = 'checked';
                        body.push('<td style="text-align:' + option.colsAlign[ii] + ';width:' + option.colsWidth[ii] + 'px;"><input name="' + option.cols[ii] + '" col="' + option.cols[ii] + '" type="checkbox" ' + checked + ' disabled></td>')
                    }
                }
                body.push('<td style="width:' + option.surplus + 'px"></td>')
                body.push('</tr>')
            }
            body.push('</tbody>')
            return body.join('');
        }

        SGrid.prototype.init_foot = function (option) {
            var cnt = GRID_COUNT;
            var tfoot = new Array();
            var tfoot_colspan = option.cols.length;

            if (option.check)tfoot_colspan++;
            if (option.count)tfoot_colspan++;
            tfoot.push('<tfoot style="width:' + option.width + 'px; top:' + (option.height + TITLE_HEIGHT) + 'px">')
            tfoot.push('<tr>')
            tfoot.push('<td colspan="' + tfoot_colspan + '">')
            if (option.btn.ins)tfoot.push('<i class="fa fa-plus SGrid-btn SGrid-btn-plus' + cnt + '"></i>')
            if (option.btn.del)tfoot.push('<i class="fa fa-minus SGrid-btn SGrid-btn-del' + cnt + '"></i>')
            if (option.btn.upd || option.btn.ins)tfoot.push('<i class="fa fa-save SGrid-btn SGrid-btn-save' + cnt + '"></i>')
            if (option.btn.find)tfoot.push('<i class="fa fa-search SGrid-btn SGrid-btn-find' + cnt + '"></i>')
            if (option.btn.refresh)tfoot.push('<i class="fa fa-refresh SGrid-btn SGrid-btn-refresh' + cnt + '"></i>')
            if (option.btn.excel)tfoot.push('<i class="fa fa-file-excel-o SGrid-btn SGrid-btn-excel' + cnt + '"></i>')
            tfoot.push('</td>')
            tfoot.push('</tr>')
            tfoot.push('</tfoot>')
            return tfoot.join('')
        }

        SGrid.prototype.btnEvent = function (element, option, cnt) {
            if (option.btn.ins) {
                $(document).on('click', '.SGrid-btn-plus' + cnt, function () {

                    var idx = 0;
                    $('#SGrid-table' + cnt).find('td').each(function () {
                        var scrollTop = $('#SGrid-table' + cnt).scrollTop();
                        if ($(this).offset().top > scrollTop) {
                            idx = $(this).closest('tr').index();
                            if (idx > 2)idx = idx + 5;
                            return false;
                        }
                    })
                    var ins_html = new Array();
                    ins_html.push('<tr>')
                    if (option.count)ins_html.push('<td class="SGrid-state" style="color:red;">I</td>')
                    if (option.check)ins_html.push('<td class="SGrid-check"><input type="checkbox" checked/></td>')
                    function setColumn() {
                        if (option.colsType[i] == 'text')ins_html.push('<td style="width:' + option.colsWidth[i] + 'px"><input class="form-control" type="text" col="' + option.cols[i] + '" name="' + option.cols[i] + '"/></td>')
                        if (option.colsType[i] == 'checkbox')ins_html.push('<td style="width:' + option.colsWidth[i] + 'px; text-align:' + option.colsAlign[i] + '"><input type="checkbox"  col="' + option.cols[i] + '" name="' + option.cols[i] + '"/></td>')
                        if (typeof option.colsType[i] == 'object')ins_html.push('<td style="width:' + option.colsWidth[i] + 'px">' + SGrid._dataObject(option.colsType[i], '', option.cols[i], option.colsWidth[i]) + '</td>');
                    }
                    for (var i in option.cols) {
                        var surplus = 0;
                        if(typeof option.colsFileInsBtn == 'object') {
                            if(option.cols[i] != option.colsFileInsBtn.mappingCol) {
                                setColumn();
                            } else {
                                ins_html.push('<td class="file-td" style="width:' + option.colsWidth[i] + 'px;"><input type="file" class="file-input"/><button class="file-btn file-btn-success file-btn-sm">'+option.colsFileInsBtn.text+'</button></td>')
                            }
                        } else {
                            setColumn();
                        }

                    }
                    // if(option.colsFileInsBtn.mappingCol == null || option.colsFileInsBtn.mappingCol == "")if(typeof option.colsFileInsBtn == "object")ins_html.push('<td class="file-td" style="width:' + option.colsWidth[i] + 'px;"><input type="file" class="file-input"/><button class="file-btn">'+option.colsFileInsBtn.text+'</button></td>')
                    ins_html.push('<td style="width:' + option.surplus + 'px"></td>')
                    ins_html.push('</tr>')
                    var lastIdx = $('#SGrid-table' + cnt).find('.SGrid-body tr:last-child').index();
                    var row = 0;
                    if(lastIdx > 0) {
                        $('#SGrid-table' + cnt).find('.SGrid-body').find('tr').eq(idx).after(ins_html.join(''))
                    } else {
                        $('#SGrid-table' + cnt).find('.SGrid-body').append(ins_html.join(''))
                    }
                    row = $('#SGrid-table' + cnt).find('.SGrid-body tr:last-child').index();
                    row = row + 1;
                    $(element).trigger(EVENT_KEY + 'ins_after', [row])
                })
            }
            if (option.btn.del) {
                $(document).on('click', '.SGrid-btn-del' + cnt, function () {
                    try {
                        if(option.key == null){alert('option 에 key 가 정의되어 있지 않습니다.');return false;}
                        var del = [];

                        $('#SGrid-table'+cnt+' .SGrid-check :checked').each(function() {
                            var list = {};
                            var state = $(this).closest('tr').find('.SGrid-state').text()
                            if(state == 'D')list[option.key] = $(this).closest('tr').find('td[col='+option.key+']').text();
                            del.push(list);
                        })
                        if(del.length >0)option.functional.del(del);
                        else alert('삭제할 데이터가 없습니다.');
                    } catch(e) {
                        alert("del : " + e)
                    }
                })
            }
            if (option.btn.upd) {
                $(document).on('dblclick', '#SGrid-table' + cnt + ' tr', function () {
                    var tr = $(this);
                    var state = tr.find('.SGrid-state').text();
                    if (state != 'U' && state != 'D' && state != 'I') {
                        tr.data('ori_data', tr.html());
                        for (var i in option.cols) {
                            var val = $(this).find('td[col=' + option.cols[i] + ']').text();
                            if (option.colsType[i] == 'text' || option.colsType[i] == 'hide')$(this).find('td[col=' + option.cols[i] + ']').text('').wrapInner('<input class="form-control" col="' + option.cols[i] + '" name="' + option.cols[i] + '" value="' + val + '">');
                            if (typeof option.colsType[i] == 'object')$(this).find('td[col=' + option.cols[i] + ']').text('').wrapInner(SGrid._dataObject(option.colsType[i], $(this).find('td[col=' + option.cols[i] + ']').attr('data-select-cd'), option.cols[i]));
                            if (option.colsType[i] == 'checkbox')$(this).find('input[col=' + option.cols[i] + ']').removeAttr('disabled');
                            $(this).find('.SGrid-state').text('U').css('color', 'red');
                            $(this).find('.SGrid-check input').prop('checked', true);
                        }
                    }

                })
            }
            if (option.btn.upd || option.btn.ins) {
                $(document).on('click', '.SGrid-btn-save' + cnt, function () {
                    try {
                        var ins = [];
                        var upd = [];
                        var insData = new FormData();
                        $('#SGrid-table'+cnt+' .SGrid-check :checked').each(function() {
                            var state = $(this).closest('tr').find('.SGrid-state').text()
                            var val = '';
                            var list = {};
                            for(var i in option.cols) {
                                var col = option.cols[i];

                                if(option.colsType[i] == 'checkbox')val = $(this).closest('tr').find('input[name='+col+']').prop('checked');
                                else if(Object.getOwnPropertyNames(option.colsType[i])[0] == 'select')val = $(this).closest('tr').find('select[name='+col+']').val();
                                else val = $(this).closest('tr').find('input[name='+col+']').val();
                                if(state == "I")list[col] = val;
                                if(state == "U")list[col] = val;
                                if(typeof option.colsFileInsBtn == 'object')if(state == 'I')insData.append(col, val);
                            }
                            if(typeof option.colsFileInsBtn == 'object') if(state == 'I')insData.append('file', $(this).closest('tr').find('input[type=file]')[0].files[0]);

                            if(state == 'U')upd.push(list);
                            if(state == 'I')ins.push(list);
                        })
                        if(ins.length > 0) {
                            if(typeof option.colsFileInsBtn == 'object')option.functional.ins(insData);
                            else option.functional.ins(ins);
                        }
                        if(upd.length > 0)option.functional.upd(upd);
                        if(upd.length == 0 && ins.length == 0)alert('저장, 수정할 데이터가 없습니다.');
                    } catch(e) {
                        alert('save : ' + e);
                    }
                })
            }
            if (option.btn.find) {
                $(document).on('click', '.SGrid-btn-find' + cnt, function () {
                    var find_html = new Array();
                    var kind_colspan = 0;
                    if (option.check)kind_colspan++;
                    if (option.count)kind_colspan++;

                    find_html.push('<tr>')
                    find_html.push('<td colspan="' + kind_colspan + '" class="SGrid-kind-td">')
                    find_html.push('<select id="SGrid-kind"  class="form-control">')
                    find_html.push('<option></option>')
                    for (var i in option.cols) {
                        if (option.colsType[i] != 'hide')find_html.push('<option value="' + option.cols[i] + '">' + option.header[i] + '</option>')
                    }
                    find_html.push('</select>')
                    find_html.push('</td>')
                    find_html.push('<td colspan="' + option.cols.length + '" class="SGrid-search-td"">')
                    find_html.push('<div style="position:relative;"><input type="text" id="SGrid-search" class="form-control" style="padding-top: 0px;padding-bottom: 0px;"><div class="SGrid-search-close"><span>&times;</span></div></div>')
                    find_html.push('</td>')
                    find_html.push('</tr>')
                    $('#SGrid-table' + cnt).find('thead').append(find_html.join(''))
                    $('#SGrid-table' + cnt).find('tbody tr:first-child td').css('padding-top', $('#SGrid-table' + cnt).find('thead').height() + 'px')


                })

                $(document).on('change', '#SGrid-table' + cnt + ' #SGrid-kind', function () {
                    var kind = $(this).val()
                    for (var i in option.cols) {
                        if (option.cols[i] == kind) {
                            if (typeof option.colsType[i] == 'object') {
                                var b = Object.getOwnPropertyNames(option.colsType[i])
                                if (b[0] == 'select') {
                                    $(this).closest('tr').find('.SGrid-search-td').empty();
                                    $(this).closest('tr').find('.SGrid-search-td').append('<div style="position:relative;"><div class="SGrid-search-close"><span>&times;</span></div>' + SGrid._dataObject(option.colsType[i], '', 'SGrid-search', option.colsWidth[i])) + '</div>'
                                }
                            }
                            else {
                                $(this).closest('tr').find('.SGrid-search-td').empty();
                                $(this).closest('tr').find('.SGrid-search-td').append('<div style="position:relative;"><input type="text" id="SGrid-search" class="form-control"  style="padding-top: 0px;padding-bottom: 0px;"><div class="SGrid-search-close"><span>&times;</span></div></div>');
                            }
                        }
                    }
                })

                $(document).on('click', '#SGrid-table' + cnt + ' .SGrid-search-close', function () {
                    $(this).closest('tr').remove();
                    $('#SGrid-table' + cnt).find('tbody tr:first-child td').css('padding-top', $('#SGrid-table' + cnt).find('thead').height() + 'px');
                    option.NOWDATA = option.ORIDATA;
                    if($('#SGrid-table'+cnt+' .SGrid-search-td:last').index() > -1) {
                        $('#SGrid-table'+cnt+' .SGrid-search-td').each(function() {
                            $(this).find('#SGrid-search').trigger('change');
                        })
                    } else {
                        SGrid.prototype.reFresh(option, cnt);
                    }
                })

                $(document).on('change', '#SGrid-table' + cnt + ' #SGrid-search', function () {
                    var kind = $(this).closest('tr').find('.SGrid-kind-td select').val();
                    var search = $(this).val();
                    if (kind != "" && kind != null) {
                        var searchData = [];
                        $(option.NOWDATA).each(function () {
                            if (this[kind].toString().indexOf(search) > -1)searchData.push(this);
                        })
                        option.NOWDATA = searchData;
                        SGrid.prototype.reFresh(option, cnt);
                        $('#SGrid-table' + cnt).find('tbody tr:first-child td').css('padding-top', $('#SGrid-table' + cnt).find('thead').height() + 'px')
                    }
                })
            }
            if (option.btn.refresh) {
                $(document).on('click', '.SGrid-btn-refresh' + cnt, function () {
                    option.NOWDATA = option.ORIDATA;
                    SGrid.prototype.reFresh(option, cnt);
                })
            }
            if (option.btn.excel) {
                $(document).on('click', '.SGrid-btn-excel' + cnt, function () {
                    alert();
                })
            }
        }

        SGrid.prototype.callbackEvent = function (element, option, cnt) {
            $(document).on('click', '#SGrid-table' + cnt + ' tbody tr', function () {
                var state = $(this).find('.SGrid-state').text()
                if (state != 'U' && state != 'I' && state != 'D') {
                    var result = {};
                    for (var i in option.cols) {
                        if (option.colsType[i] != 'checkbox')result[option.cols[i]] = $(this).find('td[col=' + option.cols[i] + ']').text()
                        else result[option.cols[i]] = $(this).find('input[col=' + option.cols[i] + ']').prop('checked')
                    }
                    $(element).trigger(EVENT_KEY + 'data', [result]);
                }
            })

            $(document).on('click', '#SGrid-table' + cnt + ' tbody tr', function () {
                var state = $(this).find('.SGrid-state').text()
                if (state != 'U' && state != 'I' && state != 'D') {
                    var result = {};
                    for (var i in option.cols) {
                        if (option.colsType[i] != 'checkbox')result[option.cols[i]] = $(this).find('td[col=' + option.cols[i] + ']').text()
                        else result[option.cols[i]] = $(this).find('input[col=' + option.cols[i] + ']').prop('checked')
                    }
                    $(element).trigger(EVENT_KEY + 'onclick', [result]);
                }
            })
        }

        SGrid._dataObject = function _dataObject(data, val, col, width) {
            var type = Object.getOwnPropertyNames(data);
            var html = new Array();

            if (type[0] == 'select') {
                html.push('<select col="' + col + '" name="' + col + '" id="' + col + '" style="width:100%;" class="form-control">')
                html.push('<option></option>')
                for (var i in data[type[0]]) {
                    var selected = '';
                    if (i == val)selected = 'selected';
                    html.push('<option value="' + i + '" ' + selected + '>' + data[type[0]][i] + '</option>');
                }
                html.push('</select>')
            }

            return html.join('')
        }

        SGrid.event = function (obj, option, cnt) {
            obj.reData = function(data) {
                option.ORIDATA = data;
                option.NOWDATA = option.ORIDATA;
                SGrid.prototype.reFresh(option, cnt)
            }

            obj.getColumnValue = function(row, col) {
                var tr = $('#SGrid-table' + cnt).find('.SGrid-body').find('tr').eq(row - 1);
                var val = '';
                if (typeof col == 'number') {
                    if (option.check)col = col + 1;
                    if (option.count)col = col + 1;
                    val = tr.find('td').eq(col - 1).find('input').val();
                }
                if (typeof col == 'string') {
                    val = tr.find('input[col=' + col + ']').val();
                }
                return val;
            }

            obj.setColumnValue = function(row, col, val) {
                var tr = $('#SGrid-table' + cnt).find('.SGrid-body').find('tr').eq(row - 1);
                if (typeof col == 'number') {
                    if (option.check)col = col + 1;
                    if (option.count)col = col + 1;
                    tr.find('td').eq(col - 1).find('input').val(val);
                }
                if (typeof col == 'string') {
                    tr.find('input[col=' + col + ']').val(val);
                }
            }

            obj.getColumnText = function (row, col) {
                var tr = $('#SGrid-table' + cnt).find('.SGrid-body').find('tr').eq(row - 1);
                var val = '';
                if (typeof col == 'number') {
                    if (option.check)col = col + 1;
                    if (option.count)col = col + 1;
                    val = tr.find('td').eq(col - 1).text();
                }
                if (typeof col == 'string') {
                    var tr = $('#SGrid-table' + cnt).find('.SGrid-body').find('tr').eq(row - 1);
                    val = tr.find('td[col=' + col + ']').text();
                }
                return val;
            }

            obj.setColumnText = function (row, col, newData) {
                var tr = $('#SGrid-table' + cnt).find('.SGrid-body').find('tr').eq(row - 1);
                if (typeof col == 'number') {
                    if (option.check)col = col + 1;
                    if (option.count)col = col + 1;
                    if (typeof newData == 'boolean')tr.find('td').eq(col - 1).find('input').prop('checked', newData);
                    else tr.find('td').eq(col - 1).text(newData);
                }
                if (typeof col == 'string') {
                    var tr = $('#SGrid-table' + cnt).find('.SGrid-body').find('tr').eq(row - 1);
                    if (typeof newData == 'boolean')tr.find('input[col=' + col + ']').prop('checked', newData);
                    else tr.find('td[col=' + col + ']').text(newData);

                }
            }

            obj.getRowKey = function(row) {
                if(option.key != null) {
                    return "";
                } else {
                    var tr = $('#SGrid-table' + cnt).find('.SGrid-body').find('tr').eq(row - 1);
                    return tr.find('td[col='+option.key+']').val();
                }
            }

            obj.getRowData = function (row) {
                var tr = $('#SGrid-table' + cnt).find('.SGrid-body').find('tr').eq(row - 1);
                var result = {};
                for (var i in option.cols) {
                    var val = '';
                    if (option.colsType[i] != 'checkbox')val = tr.find('td[col=' + option.cols[i] + ']').text()
                    if (option.colsType[i] == 'checkbox')val = tr.find('input[col=' + option.cols[i] + ']').prop('checked');
                    result[option.cols[i]] = val;
                }
                return result;
            }
        }

        SGrid._jqueryInterface = function _jqueryInterface(option, _target) {
            var cnt = GRID_COUNT;
            var obj = this.each(function() {
                var $this = $(this);
                var data = $this.data(DATA_KEY);
                var options = $.extend({}, DEFAULTS, $this.data(), typeof option == 'object' && option)
                option = options;
                if (!data) {$this.data(DATA_KEY, (data = new SGrid(this, options)));}
            })
            SGrid.event(obj, option, cnt);
            return obj;
        }
        return SGrid;
    }()

    $.fn.SGrid = SGrid._jqueryInterface;
    $.fn.SGrid.constructor = SGrid;
}(jQuery)