
require.config({
    baseUrl: "/static/js",//baseUrl + paths
    map: {
        '*': {
            'css': 'require/require-css/0.1.10/css'//配置require-css插件路径
        }
    },
    paths: {
        "$": "jquery/jquery/1.11.3/jquery.js",
        "jquery": "jquery/jquery/1.11.3/jquery",
        "$-migrate": "jquery/migrate/1.3.0/jquery-migrate",
        "jquery-migrate": "jquery/migrate/1.3.0/jquery-migrate",
        "jquery.md5": "jquery/md5/1.2.1/jquery.md5",
        "layer303": "layer/layer/3.0.3/layer",
        "layer": "layer/layer/3.1.1/layer",
        "laypage": "layer/laypage/1.3.0/laypage",
        "MISSY": "missy/missy/2.1.0/missy",
        "jquery.base64": "jquery/base64/0.0.3/jquery.base64",
        "jquery.form": "jquery/form/3.51.0/jquery.form",
        "jquery.scrollUp": "jquery/scrollup/2.4.1/jquery.scrollUp",
        "jquery.SuperSlide": "jquery/SuperSlide/2.1.1/jquery.SuperSlide",
        "jquery.autocomplete": "jquery/autocomplete/1.2.26/jquery.autocomplete.min",
        "jquery.ztree": "zTree/zTree/3.5.28/jquery.ztree.all.min",
        "i18n": "require/require-i18n/2.0.6/i18n",
        "jquery.lazyload": "jquery/lazyload/1.9.7/jquery.lazyload.min",
        "webuploader": "webuploader/0.1.5/webuploader"
    },
    shim: {
        "jquery-migrate": { deps: ["jquery"] },
        "jquery.md5": { deps: ["jquery"] },
        "layer303": { deps: ["jquery", "css!/static/js/layer/layer/3.0.3/skin/default/layer.css"] },
        "layer": { deps: ["jquery", "css!/static/js/layer/layer/3.1.1/theme/default/layer.css"] },
        "laypage": { deps: ["jquery"] },
        "MISSY": { deps: ["jquery", "layer", "laypage"] },
        "jquery.base64": { deps: ["jquery"] },
        "jquery.form": { deps: ["jquery"] },
        "jquery.scrollUp": { deps: ["jquery",  "css!/static/js/jquery/scrollup/2.4.1/skin/default.css"] },
        "jquery.SuperSlide": { deps: ["jquery"] },
        "jquery.autocomplete": { deps: ["jquery","css!/static/js/jquery/autocomplete/1.2.26/skin/default.css"]  },
        "jquery.ztree": { deps: ["jquery","css!/static/js/zTree/zTree/3.5.28/skin/zTreeStyle/zTreeStyle.css"] },
        "jquery.lazyload": { deps: ["jquery"] },
        "webuploader": { deps: ["css!/static/js/webuploader/0.1.5/skin/default/css/webuploader.css?v=1.0.2"] }
    },
    config: {
        i18n: { locale: "zh-cn" }
    },
    waitSeconds: 90
});

//为什么用require.js?
//1、模块化编程思维（代码复用、防止全局变量、解决JS文件的依赖关系。）
//2、解决JS阻塞浏览器渲染、性能得以提升(注：谷歌最新浏览器已经可以并行下载js文件了。)
//3、按需加载资源(JS)文件。