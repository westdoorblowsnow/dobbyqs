var $_paper = {
    "subjectId": 0,
    "name": "",
    "tag": "",
    "questions": []
};
var $_professions = [];



var kkkk = new Vue({
    el: '#app',
    data: {
        textareaQ: "",
        textareaA: "",
        name: "",
        tag: "",
        professionIndex: -1,
        subjectId: -1,
        professions: $_professions,
        subjects: [],
        paper: $_paper
    },
    watch: {
        professionIndex: function () {
            this.subjects = this.professions[this.professionIndex].subjects;
            this.subjectId = this.subjects[0].id;
        },
        professions: function () {
            this.professionIndex = 0;
            this.subjects = this.professions[this.professionIndex].subjects;
            this.subjectId = this.subjects[0].id;
        },
        paper: function () {
            this.$nextTick(function () {
            })
        }
    },
    methods: {
        postPA: function () {
            jQuery.ajax({
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
                    if (data.status == 1) kkkk.paper = data.data;
                    myAlert(data.info,"服务器消息")
                    // MathJax.typesetPromise();
                    jQuery("mjx-container").remove();
                    MathJax.typesetPromise().then(function () {
                        // modify the DOM here
                        MathJax.typesetPromise();
                    }).catch(function (err) {
                            console.log(err.message)
                        }
                    )
                }
            })
        },
        postUP: function () {
            jQuery.ajax({
                type: 'post',
                url: '/post/paper/insert/full',
                contentType: 'application/json;utf-8',
                data: JSON.stringify(kkkk.paper),
                dataType: 'json',
                success: function (data) {
                    alert(data.info);
                    console.log(JSON.stringify(data));
                }
            })
        }
    }
});

function myAlert(info,title) {
    alert(info);
}

function $$reloadProfession() {
    jQuery.get("/get/profession/all", function (data, status) {
        if (status == "success") {
            kkkk.professions = data.data;
        }
    })
}

$(document).ready($$reloadProfession());

