import React from "react";

const WindowForm = props => (
  <div>
    <h2>Messages In Last 60 Second Window</h2>
    <form name="filterForm" onSubmit={props.getWindowValues}>
      CurrencyFrom : <input type="text" name="currencyFrom" />
      CurrencyTo : <input type="text" name="currencyTo" />
      Originating-Country : <input type="text" name="originatingCountry" />
      <button>Get Messages in the Window</button>
    </form>
  </div>
);

export default WindowForm;