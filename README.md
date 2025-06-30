Input fields
	Customer(Name,phonenumber,address)
	order(orderId,timeforfulfillment,bubblecan quantity,coolcan quantity,status,customer)
	Store(bubblecans,coolcans)
	Vehicle(vehicleId,bubblecan quantity,coolcan quantity,capacity,Driver id,start time,end time,list<orders>,status)
	Trip -> vehicle,list of orders,Driver name,start time,endtime,status, amount collected, total expected
	Driver -> id, name, status

ORder -> user details -> order details
check if order can be fulfilled
update store
place the order


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



