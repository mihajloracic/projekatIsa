function uploadFile(){
    var file = ("file1").files[0];
    // alert(file.name+" | "+file.size+" | "+file.type);
    var formdata = new FormData();
    formdata.append("file", file);
    formdata.append("id", 1);
    var ajax = new XMLHttpRequest();
    ajax.open("POST", "api/props/image");
    ajax.send(formdata);
}