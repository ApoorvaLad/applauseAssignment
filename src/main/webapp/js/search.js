$(function() {
	$('.list-group.checked-list-box .list-group-item')
			.each(
					function() {

						// Settings
						var $widget = $(this), $checkbox = $('<input type="checkbox" class="hidden" />'), color = ($widget
								.data('color') ? $widget.data('color')
								: "primary"), style = ($widget.data('style') == "button" ? "btn-"
								: "list-group-item-"), settings = {
							on : {
								icon : 'glyphicon glyphicon-check'
							},
							off : {
								icon : 'glyphicon glyphicon-unchecked'
							}
						};

						$widget.css('cursor', 'pointer')
						$widget.append($checkbox);

						// Event Handlers
						$widget.on('click',
								function() {
									$checkbox.prop('checked', !$checkbox
											.is(':checked'));
									$checkbox.triggerHandler('change');
									updateDisplay();
								});
						$checkbox.on('change', function() {
							updateDisplay();
						});

						// Actions
						function updateDisplay() {
							var isChecked = $checkbox.is(':checked');

							// Set the button's state
							$widget.data('state', (isChecked) ? "on" : "off");

							// Set the button's icon
							$widget
									.find('.state-icon')
									.removeClass()
									.addClass(
											'state-icon '
													+ settings[$widget
															.data('state')].icon);

							// Update the button's color
							if (isChecked) {
								$widget.addClass(style + color + ' active');
							} else {
								$widget.removeClass(style + color + ' active');
							}
						}

						// Initialization
						function init() {

							if ($widget.data('checked') == true) {
								$checkbox.prop('checked', !$checkbox
										.is(':checked'));
							}

							updateDisplay();

							// Inject the icon if applicable
							if ($widget.find('.state-icon').length == 0) {
								$widget.prepend('<span class="state-icon '
										+ settings[$widget.data('state')].icon
										+ '"></span>');
							}
						}
						init();
					});

	$('#get-checked-data').on('click', function(event) {
		event.preventDefault();

		var deviceNames = [];
		var countries = [];
		var counter_devices = 0;
		var counter_countries = 0;
		$('#names li.active ').each(function(idx, li) {
			deviceNames[counter_devices] = "\"" + $(li).text() + "\"";
			counter_devices++;
		});
		console.log(deviceNames);
		$('#countries li.active').each(function(idx, li) {
			countries[counter_countries] = "\"" + $(li).text() + "\"";
			counter_countries++;
		});

		console.log(countries);
		var data = {};
		data['deviceName'] = deviceNames;
		data['countries'] = countries;
		$.ajax({
			url : "http://localhost:8080/assignment/search",
			contentType : "application/json",
			type : "post",
			data : JSON.stringify(data),
			success : function(data) {
				
				$('#display-json').html(data);
			}

		})
	});
});

function searchByDevice() {
	var deviceNames = [];
	var countries = [];
	$('.names:checked').each(function(i, e) {

	});

	$('.countries:checked').each(function(i, e) {

	});

	console.log(data);

}
