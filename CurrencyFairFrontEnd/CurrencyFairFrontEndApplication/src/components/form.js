import React from "react";

const Form = props => (
  <div>
    <h2>Filters</h2>
  <form name="filterForm" onSubmit={props.getMessages}>
    CurrencyFrom : <input type="text" name="currencyFrom" />
    CurrencyTo : <input type="text" name="currencyTo" />
    Originating-Country : <input type="text" name="originatingCountry"/>
    <button>Get Messages</button>
  </form>
  </div>
);

export default Form;