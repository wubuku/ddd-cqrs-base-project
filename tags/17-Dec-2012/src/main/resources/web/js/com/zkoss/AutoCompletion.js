com.zkoss.AutoCompletion = zk.$extends(zul.inp.FormatWidget,
				{
					_width : '10',
					_model : [],
					_value : '',
					setValue : function(val) {
						this._value = val;
					},
					getValue : function(val) {
						return this._value;
					},
					setModel : function(model) {
						this._model = model;
					},
					getModel : function() {
						return this._model;
					},
					redraw : function(out) {
						out
								.push(
										'<input type="text" rows="3" type="text" id="',
										this.uuid,
										'" class="ui-autocomplete-input" autocomplete="off" role="textbox" aria-autocomplete="list" aria-haspopup="true" size="',
										this._width, '"/>');
					},
					onSelect : function(evt) {
						zAu.send(new zk.Event(this, 'onSelect', {
							selectedItem : this._value
						}));
					},
					bind_ : function(evt) {
						this.$supers('bind_', arguments);
						var inp = this.$n();
						var wgt = this;
						$(inp).bind("keydown",
										function(event) {
											if (event.keyCode === $.ui.keyCode.TAB
													&& $(this).data("autocomplete").menu.active) {
												event.preventDefault();
											}
										});
						$(inp).bind( "autocompleteopen", function(event, ui) {
								$(ui).remove();
							});
						$(inp).autocomplete( {
							source : function(request, response) {
								response([]);
								zAu.send(new zk.Event(wgt, 'onSearch', {
									searchTerm : request.term
								}));
								response(wgt.getModel());
							},
							search : function() {
								var term = wgt.extractLast(this.value);
								if (term.length < 2) {
									return false;
								}
							},
							focus : function() {
								return false;
							},
							select : function(event, ui) {
								this.value = ui.item.desc;
								wgt.setValue(ui.item);
								wgt.onSelect(event);
								return false;
							}
						}).data("autocomplete")._renderItem = function(ul, item) {
							return $("<li></li>").data("item.autocomplete",
									item).append(
									"<a>Acc. No:" + item.id + "<br>" + item.desc
											+ "</a>").appendTo(ul);
						}
					},
					unbind_ : function(evt) {
						this.$supers('unbind_', arguments);
					},
					split : function split(val) {
						return val.split(/,\s*/);
					},
					extractLast : function extractLast(term) {
						return this.split(term).pop();
					}
				});
