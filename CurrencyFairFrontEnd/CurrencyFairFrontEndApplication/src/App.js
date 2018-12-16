import React from "react";

import Titles from "./components/title";
import Form from "./components/form";
import WindowForm from "./components/MinuteWindowForm"
import ChartForm from "./components/ChartForm"
import '../node_modules/react-linechart/dist/styles.css';
import FusionCharts from 'fusioncharts';
import Charts from 'fusioncharts/fusioncharts.charts';
import ReactFC from 'react-fusioncharts';
import FusionTheme from 'fusioncharts/themes/fusioncharts.theme.fusion';
import Maps from 'fusioncharts/fusioncharts.maps';
import World from 'fusioncharts/maps/fusioncharts.world';
ReactFC.fcRoot(FusionCharts, Maps, World,Charts,FusionTheme);

class App extends React.Component {
  state = {
    messages : [],
    messagesWindow: [],
    messagesStatistics: {
      count: 0,
      sumAmountBuy: 0,
      avgAmountBuy:0,
      maxAmountBuy:0,
      sumAmountSell:0,
      avgAmountSell:0,
      maxAmountSell:0
    },
    dataForPie: [],
    dataForBar: [],
    dataForLine: []
  }
  getWeather = async (e) => {
    e.preventDefault();
    const originatingCounty = e.target.elements.originatingCountry.value;
    const currencyFrom = e.target.elements.currencyFrom.value;
    const currencyTo = e.target.elements.currencyTo.value;
    let api_call = await fetch(`http://currencyfair-rishikesh.us-east-2.elasticbeanstalk.com/getMessagesFilter?currencyFrom=${currencyFrom}&currencyTo=${currencyTo}&originatingCountry=${originatingCounty}`);


    const data = await api_call.json();
    let messagesFromApi = data.map(
      message => {
        return {
          userId: message.userId,
          currencyFrom: message.currencyFrom,
          currencyTo: message.currencyTo,
          amountBuy: message.amountBuy,
          amountSell: message.amoutSell,
          timePlaced: message.timePlaced,
          originatingCountry: message.originatingCountry
        }
      }
    )
    this.setState({
      messages: [].concat(messagesFromApi)
    })
  }

  getWindowValues = async (e) => {
    e.preventDefault();
    const originatingCounty = e.target.elements.originatingCountry.value;
    const currencyFrom = e.target.elements.currencyFrom.value;
    const currencyTo = e.target.elements.currencyTo.value;
    let api_call = await fetch(`http://currencyfair-rishikesh.us-east-2.elasticbeanstalk.com/getMessagesInAMinuteWindow?currencyFrom=${currencyFrom}&currencyTo=${currencyTo}&originatingCountry=${originatingCounty}`);
    const data = await api_call.json();
    let messagesinWindowFromApi = data.map(
      message => {
        return {
          userId: message.userId,
          currencyFrom: message.currencyFrom,
          currencyTo: message.currencyTo,
          amountBuy: message.amountBuy,
          amountSell: message.amoutSell,
          timePlaced: message.timePlaced,
          originatingCountry: message.originatingCountry
        }
      }
    )
    this.setState({
      messagesWindow: [].concat(messagesinWindowFromApi)
    })
    let api_call_statistics = await fetch(`http://currencyfair-rishikesh.us-east-2.elasticbeanstalk.com/getMessagesInAMinuteWindowStatistics?currencyFrom=${currencyFrom}&currencyTo=${currencyTo}&originatingCountry=${originatingCounty}`);
    const data_statistics = await api_call_statistics.json();
    let messageStatisticsFromAPI = {
      count: data_statistics.transactionCount,
      sumAmountBuy: data_statistics.sumAmountBuy,
      avgAmountBuy:data_statistics.avgAmountBuy,
      maxAmountBuy:data_statistics.maxAmountBuy,
      sumAmountSell:data_statistics.sumAmountSell,
      avgAmountSell:data_statistics.avgAmountSell,
      maxAmountSell:data_statistics.maxAmountSell
    }
    this.setState({
      messagesStatistics:messageStatisticsFromAPI
    })
  }

  getChartValues = async (e) => {
    e.preventDefault();
    let api_call_pie = await fetch(`http://currencyfair-rishikesh.us-east-2.elasticbeanstalk.com/getDataForBarGraphCurrencyCount`);
    let data_pie = await api_call_pie.json();
    this.setState({
      dataForBar:[].concat(data_pie)
    });

    let api_call_bar = await fetch(`http://currencyfair-rishikesh.us-east-2.elasticbeanstalk.com/getDataForPieChart`);
    let data_bar = await api_call_bar.json();
    this.setState({
      dataForPie:[].concat(data_bar)
    });

    let api_call_line = await fetch(`http://currencyfair-rishikesh.us-east-2.elasticbeanstalk.com/getDataForLineGraph`);
    let data_line = await api_call_line.json();
    this.setState({
      dataForLine:[].concat(data_line)
    });
  }

  render() {
    let myDataSource = {
      "chart": {
        "caption": "Currency Transaction Count",
        "xAxisName": "Currency",
        "yAxisName": "Number of Transactions",
        "theme": "fusion"
      },
      "data": this.state.dataForBar
    };

    const chartConfigs = {
      type: 'column2d',
      width: 600,
      height: 400,
      dataFormat: 'json',
      dataSource: myDataSource,
    };

    let pieChartData = {
      "chart": {
        "caption": "Transaction Origin Ratio",
        "showValues": "1",
        "showPercentInTooltip": "0",
        "enableMultiSlicing": "1",
        "theme": "fusion"
      },
      "data": this.state.dataForPie
    };

    const chartConfigsPie = {
      type: 'Pie3D',
      width: 600,
      height: 400,
      dataFormat: 'json',
      dataSource: pieChartData,
    };

    let lineChartData = {
      "chart": {
        "caption": "Transaction Count Over Years",
        "yaxisname": "Transaction Count",
        "rotatelabels": "1",
        "setadaptiveymin": "1",
        "theme": "fusion"
      },
      "data": this.state.dataForLine
    };

    const chartConfigsLine ={
      type: 'line',
      width: 600,
      height: 400,
      dataFormat: 'json',
      dataSource: lineChartData,
    };

    return (
      <div>
        <div className="wrapper">
          <div className="main">
            <div className="container">
              <div className="row">
                <div className="col-xs-5 title-container">
                  <Titles />
                </div>
                <div className="col-xs-7 form-container">
                  <Form getWeather={this.getWeather} />
                  <div id='firstTable'>
                    <table >
                      <tr>
                        <th>UserId</th>
                        <th>Currency-From</th>
                        <th>Currency-To</th>
                        <th>Amount-Buy</th>
                        <th>Amount-Sell</th>
                        <th>Time-Placed</th>
                        <th>Originating-Country</th>
                      </tr>
                      {this.state.messages.map(
                        (message) => {return (
                          <tr>
                            <td>{message.userId}</td>
                            <td>{message.currencyFrom}</td>
                            <td>{message.currencyTo}</td>
                            <td>{message.amountBuy}</td>
                            <td>{message.amountSell}</td>
                            <td>{message.timePlaced}</td>
                            <td>{message.originatingCountry}</td>
                          </tr>
                        )}
                      )}
                    </table>
                  </div>
                  <div>
                    <div>
                      <WindowForm getWindowValues={this.getWindowValues}/>
                      <div>
                        <div id='secondTable'>
                          <table >
                            <tr>
                              <th>UserId</th>
                              <th>Currency-From</th>
                              <th>Currency-To</th>
                              <th>Amount-Buy</th>
                              <th>Amount-Sell</th>
                              <th>Time-Placed</th>
                              <th>Originating-Country</th>
                            </tr>
                            {this.state.messagesWindow.map(
                              (message) => {return (
                                <tr>
                                  <td>{message.userId}</td>
                                  <td>{message.currencyFrom}</td>
                                  <td>{message.currencyTo}</td>
                                  <td>{message.amountBuy}</td>
                                  <td>{message.amountSell}</td>
                                  <td>{message.timePlaced}</td>
                                  <td>{message.originatingCountry}</td>
                                </tr>
                              ) }
                            )}
                          </table>
                        </div>
                        <div>
                          <h3>Statistics</h3>
                          <li>Count : {this.state.messagesStatistics.count}</li>
                          <li>Sum - AmountBuy : {this.state.messagesStatistics.sumAmountBuy}</li>
                          <li>Avg - AmountBuy : {this.state.messagesStatistics.avgAmountBuy}</li>
                          <li>Highest - AmountBuy : {this.state.messagesStatistics.maxAmountBuy}</li>
                          <li>Sum - Amount Sell : {this.state.messagesStatistics.sumAmountSell}</li>
                          <li>Avg - Amount Sell : {this.state.messagesStatistics.avgAmountSell}</li>
                          <li>Highest - Amount Sell : {this.state.messagesStatistics.maxAmountSell}</li>
                        </div>
                      </div>
                      <div>
                        <div id="updateGraph">
                          <ChartForm getChartValues = {this.getChartValues}/>
                        </div>
                        <div >
                          <h3>Count LineChart</h3>
                          <ReactFC {...chartConfigs}/>
                        </div>
                        <div >
                          <h3>PieChart</h3>
                          <ReactFC {...chartConfigsPie}/>
                        </div>
                        <div className="App">
                          <h3>LineChart</h3>
                          <ReactFC {...chartConfigsLine}/>
                        </div>
                      </div>
                    </div>
                    <div>

                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    );
  }
};

export default App;