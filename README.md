Input fields
	Customer(Name,phonenumber,address)
	order(orderId,timeforfulfillment,bubblecan quantity,coolcan quantity,status,customer)
	Store(bubblecans,coolcans)
	Vehicle(vehicleId,bubblecan quantity,coolcan quantity,capacity,,status)
	Trip -> vehicle id,list of orders,Driver Id,start time,endtime,status, amount collected, total expected,list<orders> ordertobefulfolled	
	Driver -> id, name, status

ORder -> user details -> order details
check if order can be fulfilled
update store
place the order
reject the order
return list of pending orders
return list of rejected orders


pending orders -> add

start trip -> 
	List of Drivers/vehicles with status as Ready to go
	check for vehicle -> update the status
	check for driver -> update the status
	Load the vehicle -> update the store
	getListOfpending orders that can be fulfilled -> update the order status to in progress
	

endtrip -> trip id,List<unfulfilled orders>, amount collected
	update status of the vehicle
	update status of the driver to cooldown
	update the order statuses to complete/unfulfilled
	

Vehicle 

order is completed -> update the order status and remove the order from pending


order status -> SUBMITTED,ACCEPTED,INPROGRESS,COMPLETED,UNFULFILLED
VEHICLE STATUS -> READY,RUNNING,BROKEN,ONBREAK
DRIVER STATUS -> READY,RUNNING,ONBREAK,ONLEAVE


if the order is rejected , can we pick the order once we the store is updated?
