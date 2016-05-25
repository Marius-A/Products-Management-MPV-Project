package helper.transfer;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import entities.productEntities.Product;

public class TransferableArrayList extends ArrayList<Product> implements Transferable{
	private static final long serialVersionUID = 1103618933697697593L;
	
	public TransferableArrayList() {
		super();
	}
	public TransferableArrayList(ArrayList<Product> c) {
		super(c);	
	}
	public TransferableArrayList(Collection<? extends Product> c) {
		super(c);
	}
	public TransferableArrayList(int initialCapacity) {
		super(initialCapacity);
		// TODO Auto-generated constructor stub
	}
	@Override
	public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {
		DataFlavor prodDataFlavor = new DataFlavor(TransferableArrayList.class,Product.class.getSimpleName());
		if (flavor.equals(prodDataFlavor))
			return this;
		else
			throw new UnsupportedFlavorException(flavor);
	}
	@Override
	public DataFlavor[] getTransferDataFlavors() {
		DataFlavor prodDataFlavor = new DataFlavor(TransferableArrayList.class,Product.class.getSimpleName());
		return new DataFlavor[] { prodDataFlavor };
	}
	@Override
	public boolean isDataFlavorSupported(DataFlavor flavor) {
		DataFlavor prodDataFlavor = new DataFlavor(TransferableArrayList.class,Product.class.getSimpleName());
		return flavor.equals(prodDataFlavor);
	}
}
