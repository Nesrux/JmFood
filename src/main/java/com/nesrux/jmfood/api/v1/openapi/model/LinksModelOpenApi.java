package com.nesrux.jmfood.api.v1.openapi.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("Links")
public class LinksModelOpenApi {
	private LinkModel rel;

	@Getter
	@Setter
	@ApiModel("Link")
	private class LinkModel {
		@ApiModelProperty(example = "localhost:8080/**", position = 1)
		private String href;
		@ApiModelProperty(example = "false", position = 2)
		private boolean templated;
	}
}
