
function (out) {
	out.push('<span', this.domAttrs_(), '>', this.getEncodedText(), '<strong class="labelReq">*</strong></span>');
}