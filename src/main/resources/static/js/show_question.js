var kkkk = new Vue({
    el: '#app',
    data: {
        textareaQ: "",
        textareaA: "",
        professionIndex: -1,
        subjectId:-1,
        professions:[],
        subjects:[],
        name: "",
        tag: "",
        post_paper: {
            "questions": []
        }
    },
    watch:{
        professionIndex:function () {
            this.subjects = this.professions[this.professionIndex].subjects;
            this.subjectId = this.subjects[0].id;
        },
        professions:function () {
            this.professionIndex = 0;
            this.subjects = this.professions[this.professionIndex].subjects;
            this.subjectId = this.subjects[0].id;
        },
        post_paper:function (value) {

        }
    },
    methods: {
        postPA: function () {
            $.ajax({
                type: 'post',
                url: '/paperparser',
                contentType: 'application/json;utf-8',
                data: JSON.stringify({
                    'paper': kkkk.textareaQ,
                    'answer': kkkk.textareaA,
                    'subjectId': kkkk.subjectId,
                    'name': kkkk.name,
                    'tag': kkkk.tag
                }),
                dataType: 'json',
                success: function (data) {
                    if (data.status == 1) kkkk.post_paper = data.data;
                    alert(data.info);
                }
            })
        },
        postUP: function () {
            $.ajax({
                type: 'post',
                url: '/post/paper/insert/full',
                contentType: 'application/json;utf-8',
                data: JSON.stringify(kkkk.post_paper),
                dataType: 'json',
                success: function (data) {
                    alert(data.status + " " + data.info);
                    console.log(JSON.stringify(data));
                }
            })
        }
    }
});

$(document).ready($.get("/get/profession/all", function (data, status) {
    // alert("请求题目列表");
    //     // alert(status);
    //     // alert(JSON.stringify(data));
    if (status == "success") {
        kkkk.professions = data.data;
    }
}));

