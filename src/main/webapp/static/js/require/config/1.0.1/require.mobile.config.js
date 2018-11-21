require.config({
    baseUrl: "/staticmobiledev/js",//baseUrl + paths
    map: {
        '*': {
            'css': 'require/require-css/0.1.10/css'//配置require-css插件路径
        }
    },
    paths: {
        "$": "jquery/jquery/1.4.5/jquery",
        "jquery": "jquery/jquery/1.4.5/jquery",
        "layer": "layer/layer/3.0.3/layer",
        "MISSY": "missy/missy/2.1.0/missy"

    },
    shim: {
        "layer": { deps: ["jquery", "css!/staticmobiledev/js/layer/layer/3.0.3/need/layer.css"] },
        "MISSY": { deps: ["jquery", "layer"] }
    },
    config: {
        i18n: { locale: "zh-cn" }
    },
    waitSeconds: 90
});