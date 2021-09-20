package co.com.appgate.restcalculator.beans;

public class OperationResponse {

		private Integer result;

		public OperationResponse(Integer result) {
			super();
			this.result = result;
		}


		public Integer getResult() {
			return result;
		}

		public void setResult(Integer result) {
			this.result = result;
		}

		@Override
		public String toString() {
			return "OperationResponse [result=" + result + "]";
		}

	}