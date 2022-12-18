package pl.put.poznan.transformer.logic.tools;

/**
 * The type Json tool filter.
 */
public abstract class FilterTool extends DecoratorTool {
    /**
     * The Filter list.
     */
    protected String[] filterList = null;

    /**
     * Instantiates a new Json tool filter.
     *
     * @param filterList the filter list
     */
    public FilterTool(String[] filterList) {
        this.filterList = filterList;
    }

    /**
     * Instantiates a new Json tool filter.
     *
     * @param tool       the tool
     * @param filterList the filter list
     */
    public FilterTool(IJsonTool tool, String[] filterList) {
        super(tool);
        this.filterList = filterList;
    }
}
