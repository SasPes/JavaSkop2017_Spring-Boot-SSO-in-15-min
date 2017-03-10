var React = require('react');
var ReactDOM = require('react-dom');

var Table = React.createClass({
    render: function render() {
        var _self = this;

        var thead = React.DOM.thead({},
                React.DOM.tr({},
                        this.props.cols.map(function (col) {
                            return React.DOM.th({}, col);
                        })));

        var tbody = this.props.rows.map(function (row) {
            return React.DOM.tr({},
                    _self.props.cols.map(function (col) {
                        return React.DOM.td({}, row[col] || "");
                    }));
        });

        return React.DOM.table({}, [thead, tbody]);
    }
});

var container = document.getElementById('example');

function httpGet(theUrl)
{
    var xmlHttp = new XMLHttpRequest();
    xmlHttp.open("GET", theUrl, false); // false for synchronous request
    xmlHttp.send(null);
    return xmlHttp.responseText;
}

var users = httpGet("http://localhost:8080/api/user/list").replace(/true/g, '"true"').replace(/false/g, '"false"');
users = JSON.parse(users);

var tableModel = {
    cols: ["id", "username", "password", "accountNonExpired", "enabled", "accountNonLocked", "credentialsNonExpired"],
    rows: users
};

ReactDOM.render(React.createElement(Table, tableModel), container);

document.getElementsByTagName("table")[0].classList.add("table");