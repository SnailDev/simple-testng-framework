function getUrlParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
    var r = window.location.search.substr(1).match(reg);  //匹配目标参数
    if (r != null) return unescape(r[2]);
    return null; //返回参数值
}

String.prototype.trim = function () {
    return this.replace(/(^\s*)|(\s*$)/g, '');
};

function DataCtrl($scope) {
    $scope.result = result;

    var jobStartTime = new Date(Date.parse(result.jobStartTime.replace(/-/g, "/")));
    var jobEndTime = new Date(Date.parse(result.jobEndTime.replace(/-/g, "/")));
    $scope.result['jobTotalTime'] = (jobEndTime.getTime() - jobStartTime.getTime()) / 1000.0;

    $.each($scope.result['suites'], function (i, suite) {
        var suiteStartTime = new Date(Date.parse(suite.suiteStartTime.replace(/-/g, "/")));
        var suiteEndTime = new Date(Date.parse(suite.suiteEndTime.replace(/-/g, "/")));
        suite['suiteTotalTime'] = (suiteEndTime.getTime() - suiteStartTime.getTime()) / 1000.0;

        suite['id'] = md5(JSON.stringify(suite));
        suite['passRate'] = suite['totalCount'] == 0 ? '' : (100 * (suite['passCount']) / (suite['totalCount'])).toFixed(2) + "%";

        $.each(suite['classes'], function (j, testclass) {
            testclass['id'] = md5(JSON.stringify(testclass));

            $.each(testclass['methods'], function (j, method) {
                method['id'] = md5(JSON.stringify(method));

                var testStartTime = new Date(Date.parse(method.testStartTime.replace(/-/g, "/")));
                var testEntTime = new Date(Date.parse(method.testEndTime.replace(/-/g, "/")));
                method['testTotalTime'] = (testStartTime.getTime() - testEntTime.getTime()) / 1000.0;
            });
        });
    });
}