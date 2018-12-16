(window.webpackJsonp=window.webpackJsonp||[]).push([[0],{16:function(e,t,n){e.exports=n(40)},24:function(e,t,n){},29:function(e,t,n){},40:function(e,t,n){"use strict";n.r(t);var a=n(0),r=n.n(a),l=n(6),c=n.n(l),u=(n(22),n(24),n(29),n(1)),s=n.n(u),i=n(3),o=n(7),m=n(8),h=n(14),d=n(9),y=n(15),g=function(){return r.a.createElement("div",null,r.a.createElement("h1",{className:"title-container__title"},"Messages DashBoard"))},E=function(e){return r.a.createElement("div",null,r.a.createElement("h2",null,"Filters"),r.a.createElement("form",{name:"filterForm",onSubmit:e.getWeather},"CurrencyFrom : ",r.a.createElement("input",{type:"text",name:"currencyFrom"}),"CurrencyTo : ",r.a.createElement("input",{type:"text",name:"currencyTo"}),"Originating-Country : ",r.a.createElement("input",{type:"text",name:"originatingCountry"}),r.a.createElement("button",null,"Get Messages")))},p=function(e){return r.a.createElement("div",null,r.a.createElement("h2",null,"Messages In Last 60 Second Window"),r.a.createElement("form",{name:"filterForm",onSubmit:e.getWindowValues},"CurrencyFrom : ",r.a.createElement("input",{type:"text",name:"currencyFrom"}),"CurrencyTo : ",r.a.createElement("input",{type:"text",name:"currencyTo"}),"Originating-Country : ",r.a.createElement("input",{type:"text",name:"originatingCountry"}),r.a.createElement("button",null,"Get Messages in the Window")))},v=function(e){return r.a.createElement("div",null,r.a.createElement("form",{name:"filterForm",onSubmit:e.getChartValues},r.a.createElement("button",null,"Update Graph")))},f=(n(33),n(4)),S=n.n(f),C=n(10),A=n.n(C),w=n(2),F=n.n(w),x=n(11),B=n.n(x),b=n(12),T=n.n(b),k=n(13),j=n.n(k);F.a.fcRoot(S.a,T.a,j.a,A.a,B.a);var W=function(e){function t(){var e,n;Object(o.a)(this,t);for(var a=arguments.length,r=new Array(a),l=0;l<a;l++)r[l]=arguments[l];return(n=Object(h.a)(this,(e=Object(d.a)(t)).call.apply(e,[this].concat(r)))).state={messages:[],messagesWindow:[],messagesStatistics:{count:0,sumAmountBuy:0,avgAmountBuy:0,maxAmountBuy:0,sumAmountSell:0,avgAmountSell:0,maxAmountSell:0},dataForPie:[],dataForBar:[],dataForLine:[]},n.getWeather=function(){var e=Object(i.a)(s.a.mark(function e(t){var a,r,l,c,u,i;return s.a.wrap(function(e){for(;;)switch(e.prev=e.next){case 0:return t.preventDefault(),a=t.target.elements.originatingCountry.value,r=t.target.elements.currencyFrom.value,l=t.target.elements.currencyTo.value,e.next=6,fetch("http://currencyfair-rishikesh.us-east-2.elasticbeanstalk.com/getMessagesFilter?currencyFrom=".concat(r,"&currencyTo=").concat(l,"&originatingCountry=").concat(a));case 6:return c=e.sent,e.next=9,c.json();case 9:u=e.sent,i=u.map(function(e){return{userId:e.userId,currencyFrom:e.currencyFrom,currencyTo:e.currencyTo,amountBuy:e.amountBuy,amountSell:e.amoutSell,timePlaced:e.timePlaced,originatingCountry:e.originatingCountry}}),n.setState({messages:[].concat(i)});case 12:case"end":return e.stop()}},e,this)}));return function(t){return e.apply(this,arguments)}}(),n.getWindowValues=function(){var e=Object(i.a)(s.a.mark(function e(t){var a,r,l,c,u,i,o,m,h;return s.a.wrap(function(e){for(;;)switch(e.prev=e.next){case 0:return t.preventDefault(),a=t.target.elements.originatingCountry.value,r=t.target.elements.currencyFrom.value,l=t.target.elements.currencyTo.value,e.next=6,fetch("http://currencyfair-rishikesh.us-east-2.elasticbeanstalk.com/getMessagesInAMinuteWindow?currencyFrom=".concat(r,"&currencyTo=").concat(l,"&originatingCountry=").concat(a));case 6:return c=e.sent,e.next=9,c.json();case 9:return u=e.sent,i=u.map(function(e){return{userId:e.userId,currencyFrom:e.currencyFrom,currencyTo:e.currencyTo,amountBuy:e.amountBuy,amountSell:e.amoutSell,timePlaced:e.timePlaced,originatingCountry:e.originatingCountry}}),n.setState({messagesWindow:[].concat(i)}),e.next=14,fetch("http://currencyfair-rishikesh.us-east-2.elasticbeanstalk.com/getMessagesInAMinuteWindowStatistics?currencyFrom=".concat(r,"&currencyTo=").concat(l,"&originatingCountry=").concat(a));case 14:return o=e.sent,e.next=17,o.json();case 17:m=e.sent,h={count:m.transactionCount,sumAmountBuy:m.sumAmountSell,avgAmountBuy:m.avgAmountSell,maxAmountBuy:m.maxAmountSell,sumAmountSell:m.sumAmountBuy,avgAmountSell:m.avgAmountBuy,maxAmountSell:m.maxAmountBuy},n.setState({messagesStatistics:h});case 20:case"end":return e.stop()}},e,this)}));return function(t){return e.apply(this,arguments)}}(),n.getChartValues=function(){var e=Object(i.a)(s.a.mark(function e(t){var a,r,l,c,u,i;return s.a.wrap(function(e){for(;;)switch(e.prev=e.next){case 0:return t.preventDefault(),e.next=3,fetch("http://currencyfair-rishikesh.us-east-2.elasticbeanstalk.com/getDataForBarGraphCurrencyCount");case 3:return a=e.sent,e.next=6,a.json();case 6:return r=e.sent,n.setState({dataForBar:[].concat(r)}),e.next=10,fetch("http://currencyfair-rishikesh.us-east-2.elasticbeanstalk.com/getDataForPieChart");case 10:return l=e.sent,e.next=13,l.json();case 13:return c=e.sent,n.setState({dataForPie:[].concat(c)}),e.next=17,fetch("http://currencyfair-rishikesh.us-east-2.elasticbeanstalk.com/getDataForLineGraph");case 17:return u=e.sent,e.next=20,u.json();case 20:i=e.sent,n.setState({dataForLine:[].concat(i)});case 22:case"end":return e.stop()}},e,this)}));return function(t){return e.apply(this,arguments)}}(),n}return Object(y.a)(t,e),Object(m.a)(t,[{key:"render",value:function(){var e={type:"column2d",width:600,height:400,dataFormat:"json",dataSource:{chart:{caption:"Currency Transaction Count",xAxisName:"Currency",yAxisName:"Number of Transactions",theme:"fusion"},data:this.state.dataForBar}},t={type:"Pie3D",width:600,height:400,dataFormat:"json",dataSource:{chart:{caption:"Transaction Origin Ratio",showValues:"1",showPercentInTooltip:"0",enableMultiSlicing:"1",theme:"fusion"},data:this.state.dataForPie}},n={type:"line",width:600,height:400,dataFormat:"json",dataSource:{chart:{caption:"Transaction Count Over Years",yaxisname:"Transaction Count",rotatelabels:"1",setadaptiveymin:"1",theme:"fusion"},data:this.state.dataForLine}};return r.a.createElement("div",null,r.a.createElement("div",{className:"wrapper"},r.a.createElement("div",{className:"main"},r.a.createElement("div",{className:"container"},r.a.createElement("div",{className:"row"},r.a.createElement("div",{className:"col-xs-5 title-container"},r.a.createElement(g,null)),r.a.createElement("div",{className:"col-xs-7 form-container"},r.a.createElement(E,{getWeather:this.getWeather}),r.a.createElement("div",{id:"firstTable"},r.a.createElement("table",null,r.a.createElement("tr",null,r.a.createElement("th",null,"UserId"),r.a.createElement("th",null,"Currency-From"),r.a.createElement("th",null,"Currency-To"),r.a.createElement("th",null,"Amount-Buy"),r.a.createElement("th",null,"Amount-Sell"),r.a.createElement("th",null,"Time-Placed"),r.a.createElement("th",null,"Originating-Country")),this.state.messages.map(function(e){return r.a.createElement("tr",null,r.a.createElement("td",null,e.userId),r.a.createElement("td",null,e.currencyFrom),r.a.createElement("td",null,e.currencyTo),r.a.createElement("td",null,e.amountBuy),r.a.createElement("td",null,e.amountSell),r.a.createElement("td",null,e.timePlaced),r.a.createElement("td",null,e.originatingCountry))}))),r.a.createElement("div",null,r.a.createElement("div",null,r.a.createElement(p,{getWindowValues:this.getWindowValues}),r.a.createElement("div",null,r.a.createElement("div",{id:"secondTable"},r.a.createElement("table",null,r.a.createElement("tr",null,r.a.createElement("th",null,"UserId"),r.a.createElement("th",null,"Currency-From"),r.a.createElement("th",null,"Currency-To"),r.a.createElement("th",null,"Amount-Buy"),r.a.createElement("th",null,"Amount-Sell"),r.a.createElement("th",null,"Time-Placed"),r.a.createElement("th",null,"Originating-Country")),this.state.messagesWindow.map(function(e){return r.a.createElement("tr",null,r.a.createElement("td",null,e.userId),r.a.createElement("td",null,e.currencyFrom),r.a.createElement("td",null,e.currencyTo),r.a.createElement("td",null,e.amountBuy),r.a.createElement("td",null,e.amountSell),r.a.createElement("td",null,e.timePlaced),r.a.createElement("td",null,e.originatingCountry))}))),r.a.createElement("div",null,r.a.createElement("h3",null,"Statistics"),r.a.createElement("li",null,"Count : ",this.state.messagesStatistics.count),r.a.createElement("li",null,"Sum - AmountBuy : ",this.state.messagesStatistics.sumAmountBuy),r.a.createElement("li",null,"Avg - AmountBuy : ",this.state.messagesStatistics.avgAmountBuy),r.a.createElement("li",null,"Highest - AmountBuy : ",this.state.messagesStatistics.maxAmountBuy),r.a.createElement("li",null,"Sum - Amount Sell : ",this.state.messagesStatistics.sumAmountSell),r.a.createElement("li",null,"Avg - Amount Sell : ",this.state.messagesStatistics.avgAmountSell),r.a.createElement("li",null,"Highest - Amount Sell : ",this.state.messagesStatistics.maxAmountSell))),r.a.createElement("div",null,r.a.createElement("div",{id:"updateGraph"},r.a.createElement(v,{getChartValues:this.getChartValues})),r.a.createElement("div",null,r.a.createElement("h3",null,"Count LineChart"),r.a.createElement(F.a,e)),r.a.createElement("div",null,r.a.createElement("h3",null,"PieChart"),r.a.createElement(F.a,t)),r.a.createElement("div",{className:"App"},r.a.createElement("h3",null,"LineChart"),r.a.createElement(F.a,n)))),r.a.createElement("div",null))))))))}}]),t}(r.a.Component);Boolean("localhost"===window.location.hostname||"[::1]"===window.location.hostname||window.location.hostname.match(/^127(?:\.(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)){3}$/));c.a.render(r.a.createElement(W,null),document.getElementById("root")),"serviceWorker"in navigator&&navigator.serviceWorker.ready.then(function(e){e.unregister()})}},[[16,2,1]]]);
//# sourceMappingURL=main.6c88848b.chunk.js.map